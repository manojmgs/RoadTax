package com.volvo.congestioncalculator.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.volvo.congestioncalculator.model.TimeandTaxModel2013;

@Configuration
@PropertySource("classpath:gothenCityTimeandTax.properties")
@ConfigurationProperties(prefix = "gothen")
public class GothenburgTimeandTaxConfig {
	private String currency;
	private int maxAmountPerDay;
	private int singleChargeRuleTime;
	private String dateTimeFormat;
	private String[] publicHolidays;
	private String[] taxFreeMonth;
	private List<TimeandTaxModel2013> timeandTaxModel2013;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDateTimeFormat() {
		return dateTimeFormat;
	}

	public void setDateTimeFormat(String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}

	public int getSingleChargeRuleTime() {
		return singleChargeRuleTime;
	}

	public void setSingleChargeRuleTime(int singleChargeRuleTime) {
		this.singleChargeRuleTime = singleChargeRuleTime;
	}

	public int getMaxAmountPerDay() {
		return maxAmountPerDay;
	}

	public void setMaxAmountPerDay(int maxAmountPerDay) {
		this.maxAmountPerDay = maxAmountPerDay;
	}

	public String[] getPublicHolidays() {
		return publicHolidays;
	}

	public void setPublicHolidays(String[] publicHolidays) {
		this.publicHolidays = publicHolidays;
	}

	public String[] getTaxFreeMonth() {
		return taxFreeMonth;
	}

	public void setTaxFreeMonth(String[] taxFreeMonth) {
		this.taxFreeMonth = taxFreeMonth;
	}

	public List<TimeandTaxModel2013> getTimeandTaxModel2013() {
		return timeandTaxModel2013;
	}

	public void setTimeandTaxModel2013(List<TimeandTaxModel2013> timeandTaxModel2013) {
		this.timeandTaxModel2013 = timeandTaxModel2013;
	}

	@Override
	public String toString() {
		return "GothenburgTimeandTaxConfig [timeandTaxModel=" + timeandTaxModel2013 + "]";
	}

}
