package com.volvo.congestioncalculator.model;

import java.util.Arrays;

public class VehicleTaxModel {
	
	 private String vehicleType;
	 private String vehicleSubType;
	 private String vehicleNumber;
	 private String[] dates;
	 
	 
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleSubType() {
		return vehicleSubType;
	}
	public void setVehicleSubType(String vehicleSubType) {
		this.vehicleSubType = vehicleSubType;
	}
	public String[] getDates() {
		return dates;
	}
	public void setDates(String[] dates) {
		this.dates = dates;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	@Override
	public String toString() {
		return "VehicleTaxModel [vehicleType=" + vehicleType + ", vehicleSubType=" + vehicleSubType + ", vehicleNumber="
				+ vehicleNumber + ", dates=" + Arrays.toString(dates) + "]";
	}
	
	
	 
	
	 

}
