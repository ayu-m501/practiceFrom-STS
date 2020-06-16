package com.javatpoint.model;

import java.util.ArrayList;
import java.util.List;

public class EnrichedATBP {

	List<ATBPCoupon> listOfcoupon = new ArrayList<ATBPCoupon>();

	private TicketLevelInfo ticketLevelInfo;	

	public TicketLevelInfo getTicketLevelInfo() {
		return ticketLevelInfo;
	}

	public void setTicketLevelInfo(TicketLevelInfo ticketLevelInfo) {
		this.ticketLevelInfo = ticketLevelInfo;
	}

	public List<ATBPCoupon> getListOfcoupon() {
		return listOfcoupon;
	}

	public void setListOfcoupon(List<ATBPCoupon> listOfcoupon) {
		this.listOfcoupon = listOfcoupon;
	}

}
