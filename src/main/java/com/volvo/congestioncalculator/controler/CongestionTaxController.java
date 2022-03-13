package com.volvo.congestioncalculator.controler;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.volvo.congestioncalculator.config.GothenburgTimeandTaxConfig;
import com.volvo.congestioncalculator.config.TaxExemptedVehicle;
import com.volvo.congestioncalculator.enums.VehicleTypeEnum;
import com.volvo.congestioncalculator.model.Car;
import com.volvo.congestioncalculator.model.TimeandTaxModel2013;
import com.volvo.congestioncalculator.model.Vehicle;
import com.volvo.congestioncalculator.model.VehicleTaxModel;
import com.volvo.congestioncalculator.utility.TaxCalculator;

/**
 * @author manoj singh
 * @version 1.0 MainController for Application
 */
@RestController
public class CongestionTaxController {

	@Autowired
	private TaxExemptedVehicle taxExemptedVehicle;

	@Autowired
	private TaxCalculator taxCalculator;

	private static final Logger logger = LoggerFactory.getLogger(CongestionTaxController.class);

	/**
	 * Entry point of Application
	 * 
	 * @param city            optional city name
	 * @param vehicleTaxModel model to take request from client
	 * @return total tax incurred for provided vehicle in int
	 */
	@GetMapping("/tollcalculation/{city}")
	public int getExemptedVehicle(@PathVariable(required = false) String city,
			@RequestBody VehicleTaxModel vehicleTaxModel) {

		logger.debug("Tax calculation  city is " + city);

		if (null == vehicleTaxModel || vehicleTaxModel.getVehicleType().equals("")
				|| vehicleTaxModel.getDates().length <= 0) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Data");
		}
		String vehiclysubtype = vehicleTaxModel.getVehicleSubType();

		if (null != vehiclysubtype || !vehiclysubtype.equals("")) {

			List<String> exmptedVehicleList = Arrays.asList(taxExemptedVehicle.getTaxExmptedVehicles());
			if (exmptedVehicleList.stream().anyMatch(s -> s.equalsIgnoreCase(vehiclysubtype))) {
				throw new ResponseStatusException(HttpStatus.ACCEPTED,
						vehiclysubtype + "  vehicles are exempted for tax");
			}
		}
		if (vehicleTaxModel.getVehicleType().equals(VehicleTypeEnum.valueOf("CAR").toString())) {
			Car vehicle = new Car();
			vehicle.setVehicleNumber(vehicleTaxModel.getVehicleNumber());
			vehicle.setVehicleType(vehicleTaxModel.getVehicleType());
			vehicle.setVehicleSubType(vehicleTaxModel.getVehicleSubType());
			logger.debug("Tax calculation  info are " + vehicleTaxModel.toString());
			return taxCalculator.getGothenTax(vehicle, vehicleTaxModel.getDates());
		} else {
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleType(vehicleTaxModel.getVehicleType());
			vehicle.setVehicleSubType(vehicleTaxModel.getVehicleSubType());
			logger.debug("Tax calculation  info are " + vehicleTaxModel.toString());
			return taxCalculator.getGothenTax(vehicle, vehicleTaxModel.getDates());

		}
	}

}
