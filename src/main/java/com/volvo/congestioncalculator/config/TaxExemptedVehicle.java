package com.volvo.congestioncalculator.config;

import java.util.Arrays;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "taxfree")
public class TaxExemptedVehicle {

	private String[] taxExmptedVehicles;

	public String[] getTaxExmptedVehicles() {
		return taxExmptedVehicles;
	}

	public void setTaxExmptedVehicles(String[] taxExmptedVehicles) {
		this.taxExmptedVehicles = taxExmptedVehicles;
	}

	@Override
	public String toString() {
		return "TaxExemptedVehicle [taxExmptedVehicles=" + Arrays.toString(taxExmptedVehicles) + "]";
	}

}
