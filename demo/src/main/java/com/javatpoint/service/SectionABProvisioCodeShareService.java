package com.javatpoint.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.javatpoint.exception.KeyToken;
import com.javatpoint.model.ATBPCoupon;
import com.javatpoint.model.EnrichedATBP;
import com.javatpoint.model.ProvisoCodeShareModel;
import com.javatpoint.model.ProvisoMainModel;

public class SectionABProvisioCodeShareService {

	@Autowired
	ProvisoMainModelService provisoMainModelService;

	@Autowired
	ProvisoCodeShareService provisoCodeShareService;

	private static Logger logger = LoggerFactory.getLogger(SectionABProvisioCodeShareService.class);

	public final static String DATEPATTERN = "yyyy/mm/dd";

	public EnrichedATBP setProvisioCodeShare(EnrichedATBP enrichedATBP) {

		logger.debug("section a,b started");

		int atbpCount = 0;

		List<ATBPCoupon> atbpCouponList = enrichedATBP.getListOfcoupon();

		for (int i = 0; i < atbpCouponList.size(); i++) {

			Boolean singleAtbpFlag = false;
			atbpCount++;
			if (atbpCouponList.get(i).getAtbpValue() != null && atbpCount == 1) {
				singleAtbpFlag = true;
			}

			if (!checkValidCoupon(enrichedATBP, i) && (!singleAtbpFlag)
					&& (enrichedATBP.getListOfcoupon().get(i).getSrpMethod() != null
							&& enrichedATBP.getListOfcoupon().get(i).getSrpMethod().equals("ICH"))
					&& (enrichedATBP.getTicketLevelInfo().getTicketLevelPaxType() != null
							&& enrichedATBP.getTicketLevelInfo().getTicketLevelPaxType().equals("in"))
					&& (enrichedATBP.getTicketLevelInfo().getTicketLevelPaxType() != null
							&& enrichedATBP.getTicketLevelInfo().getTicketLevelPaxType().equals("id"))
					&& (enrichedATBP.getListOfcoupon().get(0).getFareBasisTranslation() == null || (enrichedATBP
							.getListOfcoupon().get(0).getFareBasisTranslation().getDiscountCode() == null
							|| !enrichedATBP.getListOfcoupon().get(0).getFareBasisTranslation().getDiscountCode()
									.equals("in")))
					&& (enrichedATBP.getListOfcoupon().get(0).getFareBasisTranslation() == null
							|| (enrichedATBP.getListOfcoupon().get(0).getFareBasisTranslation().getFareType() == null
									|| !enrichedATBP.getListOfcoupon().get(0).getFareBasisTranslation().getFareType()
											.equals("id")))) {

				List<ProvisoMainModel> provisoMainModelSectionA = getSerialNumberForcorrespondingCoupon(enrichedATBP, i,
						"A");

				List<ProvisoCodeShareModel> provisoCodeShareModelSectionA = null;
				if (provisoMainModelSectionA != null && (!provisoMainModelSectionA.isEmpty())) {

					provisoCodeShareModelSectionA = getProvisoCodeShareRecordFromProvisoCodeShare(
							provisoMainModelSectionA, i, enrichedATBP, "A");

					if (provisoCodeShareModelSectionA != null && !(provisoCodeShareModelSectionA.isEmpty())) {

						settingValue(provisoCodeShareModelSectionA, i, atbpCouponList);

					} else {

						List<ProvisoMainModel> provisoMainModelSectionB = getSerialNumberForcorrespondingCoupon(
								enrichedATBP, i, "B");

						List<ProvisoCodeShareModel> provisoCodeShareModelSectionB = null;
						if (provisoMainModelSectionB != null && (!provisoMainModelSectionB.isEmpty())) {

							provisoCodeShareModelSectionB = getProvisoCodeShareRecordFromProvisoCodeShare(
									provisoMainModelSectionA, i, enrichedATBP, "B");

							if (provisoCodeShareModelSectionA != null && !(provisoCodeShareModelSectionA.isEmpty())) {

								settingValue(provisoCodeShareModelSectionA, i, atbpCouponList);

							}

						}

					}

					if (atbpCouponList.get(i).getProvisoCSApply() == null) {
						atbpCouponList.get(i).setProvisoCSApply(false);
						atbpCouponList.get(i).setProvisoSection(null);
						atbpCouponList.get(i).setProvisoCXR(null);
						atbpCouponList.get(i).setProvisoSrNo(null);
						atbpCouponList.get(i).setProvisoCSRec(null);
						atbpCouponList.get(i).setProvisoCodeShareCxr(null);
					}

					if (atbpCouponList.get(i).getAtbpValue() != null) {
						atbpCount = 0;
					}
				}
			}
		}

		logger.debug("ended section a and b");
		return enrichedATBP;
	}

	public boolean checkValidCoupon(EnrichedATBP enrichedATBP, int i) {

		return (enrichedATBP.getTicketLevelInfo().getTicketIndicator().equals(KeyToken.INVOL_TICKET)
				&& enrichedATBP.getListOfcoupon().get(i).getCouponType() != null
				&& enrichedATBP.getListOfcoupon().get(i).getCouponType().equals(KeyToken.Y_COUPON));

	}

	public List<ProvisoMainModel> getSerialNumberForcorrespondingCoupon(EnrichedATBP enrichedATBP, int i,
			String section) {

		SimpleDateFormat formatter = new SimpleDateFormat(DATEPATTERN);

		String strDate = formatter.format(enrichedATBP.getTicketLevelInfo().getTicketIssueDate());
		List<ProvisoMainModel> provisoMainModel = provisoMainModelService
				.search(enrichedATBP.getListOfcoupon().get(i).getCxr(), strDate, null, section, null);
		return (provisoMainModel != null && !provisoMainModel.isEmpty() ? provisoMainModel : null);

	}

	public List<ProvisoCodeShareModel> getProvisoCodeShareRecordFromProvisoCodeShare(
			List<ProvisoMainModel> provisoMainModel, int i, EnrichedATBP enrichedATBP, String section) {

		if (section.equals("A")) {

			List<ProvisoCodeShareModel> provisoCodeShareModelOriTODest = provisoCodeShareService.search(
					enrichedATBP.getListOfcoupon().get(i).getCxr(), provisoMainModel.get(0).getProvisoSequenceNumber(),
					section, "1" + enrichedATBP.getListOfcoupon().get(i).getOrigin(),
					"1" + enrichedATBP.getListOfcoupon().get(i).getDestination());

			List<ProvisoCodeShareModel> provisoCodeShareModelDestTOOri = (provisoCodeShareModelOriTODest.isEmpty())
					? provisoCodeShareService.search(enrichedATBP.getListOfcoupon().get(i).getCxr(),
							provisoMainModel.get(0).getProvisoSequenceNumber(), section,
							"1" + enrichedATBP.getListOfcoupon().get(i).getDestination(),
							"1" + enrichedATBP.getListOfcoupon().get(i).getOrigin())
					: null;

			if (provisoCodeShareModelDestTOOri != null && !(provisoCodeShareModelDestTOOri.isEmpty())) {

				if (!(provisoCodeShareModelDestTOOri.get(0).getViceVesaFlag())) {
					provisoCodeShareModelDestTOOri = null;
				}
			}

			return (provisoCodeShareModelDestTOOri.isEmpty()) ? provisoCodeShareModelDestTOOri
					: provisoCodeShareModelOriTODest;

		} else {

			List<ProvisoCodeShareModel> provisoCodeShareModelOriTODest = provisoCodeShareService.search(
					enrichedATBP.getListOfcoupon().get(i).getCxr(), provisoMainModel.get(0).getProvisoSequenceNumber(),
					section, "1" + enrichedATBP.getListOfcoupon().get(i).getOriginAirport().getCountryCode(),
					"1" + enrichedATBP.getListOfcoupon().get(i).getDestinationAirport().getCountryCode());

			List<ProvisoCodeShareModel> provisoCodeShareModelDestTOOri = (provisoCodeShareModelOriTODest.isEmpty())
					? provisoCodeShareService.search(enrichedATBP.getListOfcoupon().get(i).getCxr(),
							provisoMainModel.get(0).getProvisoSequenceNumber(), section,
							"1" + enrichedATBP.getListOfcoupon().get(i).getDestinationAirport().getCountryCode(),
							"1" + enrichedATBP.getListOfcoupon().get(i).getOriginAirport().getCountryCode())
					: null;

			if (provisoCodeShareModelDestTOOri != null && !(provisoCodeShareModelDestTOOri.isEmpty())) {

				if (!(provisoCodeShareModelDestTOOri.get(0).getViceVesaFlag())) {
					provisoCodeShareModelDestTOOri = null;
				}
			}

			return (provisoCodeShareModelDestTOOri.isEmpty()) ? provisoCodeShareModelDestTOOri
					: provisoCodeShareModelOriTODest;

		}

	}

	public void settingValue(List<ProvisoCodeShareModel> provisoCodeShareModel, int i,
			List<ATBPCoupon> atbpCouponList) {

		if (!provisoCodeShareModel.isEmpty()) {
			atbpCouponList.get(i).setProvisoCSApply(true);
			atbpCouponList.get(i).setProvisoSection(provisoCodeShareModel.get(0).getProvisoSection());
			atbpCouponList.get(i).setProvisoCXR(atbpCouponList.get(i).getCxr());
			atbpCouponList.get(i).setProvisoSrNo(provisoCodeShareModel.get(0).getProvisoSequenceNumber());
			atbpCouponList.get(i).setProvisoCSRec(provisoCodeShareModel.get(0).getCodeShareRecordNumber());
			atbpCouponList.get(i).setProvisoCodeShareCxr(provisoCodeShareModel.get(0).getCodeShareCXR());
		}
	}

}
