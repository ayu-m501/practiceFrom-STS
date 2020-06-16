package com.javatpoint.model;

public class ATBPCoupon {

	private String origin;

	private String Destination;

	private Integer atbpValue;

	private String srpMethod;

	private String cxr;

	private String couponType;

	private OneCountry originAirport;

	private OneCountry destinationAirport;

	private Boolean provisoCSApply;

	private String provisoSection;

	private String provisoCXR;

	private Integer provisoSrNo;

	private String provisoCSRec;

	private String provisoCodeShareCxr;

	public Boolean getProvisoCSApply() {
		return provisoCSApply;
	}

	public void setProvisoCSApply(Boolean provisoCSApply) {
		this.provisoCSApply = provisoCSApply;
	}

	public String getProvisoSection() {
		return provisoSection;
	}

	public void setProvisoSection(String provisoSection) {
		this.provisoSection = provisoSection;
	}

	public String getProvisoCXR() {
		return provisoCXR;
	}

	public void setProvisoCXR(String provisoCXR) {
		this.provisoCXR = provisoCXR;
	}

	public Integer getProvisoSrNo() {
		return provisoSrNo;
	}

	public void setProvisoSrNo(Integer provisoSrNo) {
		this.provisoSrNo = provisoSrNo;
	}

	public String getProvisoCSRec() {
		return provisoCSRec;
	}

	public void setProvisoCSRec(String provisoCSRec) {
		this.provisoCSRec = provisoCSRec;
	}

	public String getProvisoCodeShareCxr() {
		return provisoCodeShareCxr;
	}

	public void setProvisoCodeShareCxr(String provisoCodeShareCxr) {
		this.provisoCodeShareCxr = provisoCodeShareCxr;
	}

	public OneCountry getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(OneCountry originAirport) {
		this.originAirport = originAirport;
	}

	public OneCountry getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(OneCountry destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	private FareBasisTranslation fareBasisTranslation;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public FareBasisTranslation getFareBasisTranslation() {
		return fareBasisTranslation;
	}

	public void setFareBasisTranslation(FareBasisTranslation fareBasisTranslation) {
		this.fareBasisTranslation = fareBasisTranslation;
	}

	public Integer getAtbpValue() {
		return atbpValue;
	}

	public void setAtbpValue(Integer atbpValue) {
		this.atbpValue = atbpValue;
	}

	public String getSrpMethod() {
		return srpMethod;
	}

	public void setSrpMethod(String srpMethod) {
		this.srpMethod = srpMethod;
	}

	public String getCxr() {
		return cxr;
	}

	public void setCxr(String cxr) {
		this.cxr = cxr;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

}
