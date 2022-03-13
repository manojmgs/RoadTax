package com.volvo.congestioncalculator.utility;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.volvo.congestioncalculator.config.GothenburgTimeandTaxConfig;
import com.volvo.congestioncalculator.model.TimeandTaxModel2013;
import com.volvo.congestioncalculator.model.Vehicle;

@Component
public class TaxCalculator extends BaseTaxCalculator {

	@Autowired
	private GothenburgTimeandTaxConfig gothenburgTimeandTaxConfig;

	Map<Integer, Integer> totalTaxforDay = null;

	public int getGothenTax(Vehicle vehicle, String[] dates) {

		totalTaxforDay = new HashMap<>();

		List<LocalDateTime> sortedDateEntry = new ArrayList<>();

		for (String date : dates) {

			LocalDateTime lt = getLocalDateandTime(date, gothenburgTimeandTaxConfig.getDateTimeFormat());
			sortedDateEntry.add(lt);
		}
		Collections.sort(sortedDateEntry);

		LocalDateTime lastTaxed = null;

		for (LocalDateTime lt : sortedDateEntry) {

			if (checkExmptedDayandMonth(lt)) {
				continue;
			} else {

				int getTollFee = GetTollFee(lt, lastTaxed);
				if (getTollFee > 0) {

					lastTaxed = lt;
				}

			}

		}

		return totalTaxforDay.values().stream().reduce(0, Integer::sum);
	}

	private int GetTollFee(LocalDateTime lt, LocalDateTime lastTaxed) {

		if (null == totalTaxforDay.get(lt.getDayOfYear()))
			totalTaxforDay.put(lt.getDayOfYear(), 0);

		int getToll = 0;

		if (null != lastTaxed) {
			Duration duration = Duration.between(lastTaxed, lt);
			if (duration.toMinutes() <= gothenburgTimeandTaxConfig.getSingleChargeRuleTime())
				return 0;

		}

		List<TimeandTaxModel2013> timeandTaxModel2013 = gothenburgTimeandTaxConfig.getTimeandTaxModel2013();

		for (TimeandTaxModel2013 timeandDate : timeandTaxModel2013) {

			String[] stratTime = timeandDate.getStartTime().split(":");
			String[] endTime = timeandDate.getEndTime().split(":");

			if (lt.getHour() == Integer.parseInt(stratTime[0].trim())
					&& lt.getHour() == Integer.parseInt(endTime[0].trim())) {

				if (lt.getMinute() >= Integer.parseInt(stratTime[1].trim())
						&& lt.getMinute() <= Integer.parseInt(endTime[1].trim())) {
					getToll = timeandDate.getTaxAmount();

					totalTaxforDay.put(lt.getDayOfYear(), totalTaxforDay.get(lt.getDayOfYear()) + getToll);

					if (totalTaxforDay.get(lt.getDayOfYear()) >= gothenburgTimeandTaxConfig.getMaxAmountPerDay())
						return gothenburgTimeandTaxConfig.getMaxAmountPerDay();
					break;

				}
			}

			if (lt.getHour() >= Integer.parseInt(stratTime[0].trim())
					&& lt.getHour() < Integer.parseInt(endTime[0].trim())) {

				getToll = timeandDate.getTaxAmount();

				totalTaxforDay.put(lt.getDayOfYear(), totalTaxforDay.get(lt.getDayOfYear()) + getToll);

				if (totalTaxforDay.get(lt.getDayOfYear()) >= gothenburgTimeandTaxConfig.getMaxAmountPerDay())
					return gothenburgTimeandTaxConfig.getMaxAmountPerDay();
				break;

			}

			if (lt.getHour() > Integer.parseInt(stratTime[0].trim())
					&& lt.getHour() <= Integer.parseInt(endTime[0].trim())) {

				getToll = timeandDate.getTaxAmount();

				totalTaxforDay.put(lt.getDayOfYear(), totalTaxforDay.get(lt.getDayOfYear()) + getToll);

				if (totalTaxforDay.get(lt.getDayOfYear()) >= gothenburgTimeandTaxConfig.getMaxAmountPerDay())
					return gothenburgTimeandTaxConfig.getMaxAmountPerDay();

				break;
			}

			if (Integer.parseInt(stratTime[0].trim()) > Integer.parseInt(endTime[0].trim())) {

				getToll = timeandDate.getTaxAmount();

				totalTaxforDay.put(lt.getDayOfYear(), totalTaxforDay.get(lt.getDayOfYear()) + getToll);

				if (totalTaxforDay.get(lt.getDayOfYear()) >= gothenburgTimeandTaxConfig.getMaxAmountPerDay())
					return gothenburgTimeandTaxConfig.getMaxAmountPerDay();

				break;

			}

		}

		return getToll;
	}

}
