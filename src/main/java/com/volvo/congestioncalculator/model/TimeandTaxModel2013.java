package com.volvo.congestioncalculator.model;

public class TimeandTaxModel2013 {

	private String startTime;
	private String endTime;
	private int taxAmount;
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}
	@Override
	public String toString() {
		return "TimeandTaxModel [startTime=" + startTime + ", endTime=" + endTime + ", taxAmount=" + taxAmount + "]";
	}
	
	
	
}
