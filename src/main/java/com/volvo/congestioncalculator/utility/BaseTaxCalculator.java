package com.volvo.congestioncalculator.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.volvo.congestioncalculator.config.GothenburgTimeandTaxConfig;

/**
 * @author mansingh63
 * Base class for tax calculation
 * Child class may override its method this for their specific implementation
 *
 */
public class BaseTaxCalculator {

	@Autowired
	private GothenburgTimeandTaxConfig gothenburgTimeandTaxConfig;
	

	/**
	 * Method to convert String into LocalDateTime
	 * @param dateAndTime
	 * @param format
	 * @return LocalDateTime
	 */
	protected LocalDateTime getLocalDateandTime(String dateAndTime, String format) {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(dateAndTime, myFormatObj);

	}

	/**
	 * Method to check if it is not weekend or exempted month from tax
	 * {@link #checkPublicHoliday}
	 * @param date
	 * @return
	 */
	protected boolean checkExmptedDayandMonth(LocalDateTime date) {

		String[] exmptedMonth = gothenburgTimeandTaxConfig.getTaxFreeMonth();
		List<String> exmptedMonthList = Arrays.asList(exmptedMonth);
		boolean anyMonthMatch = exmptedMonthList.stream().anyMatch(s -> s.equalsIgnoreCase(date.getMonth().toString()));
		if (anyMonthMatch)
			return true;

		if (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY"))
			return true;
		if (checkPublicHoliday(date))
			return true;

		return false;
	}

	/**
	 * Method to check if date provide is not holiday
	 * @param date
	 * @return true if date is holiday
	 */
	protected boolean checkPublicHoliday(LocalDateTime date) {

		String[] publicHolidy = gothenburgTimeandTaxConfig.getPublicHolidays();
		List<String> publicHolidyList = Arrays.asList(publicHolidy);
		if (publicHolidyList.size() > 0) {
			boolean anyDateMatch = publicHolidyList.stream()
					.map(temDate -> (getLocalDateandTime(temDate, gothenburgTimeandTaxConfig.getDateTimeFormat()))
							.toLocalDate())
					.anyMatch(tempDate -> date.toLocalDate().equals(tempDate));
			if (anyDateMatch)
				return true;
		}

		return false;
	}

}
