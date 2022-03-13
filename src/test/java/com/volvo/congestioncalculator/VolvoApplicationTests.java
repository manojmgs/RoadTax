package com.volvo.congestioncalculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.volvo.congestioncalculator.controler.CongestionTaxController;
import com.volvo.congestioncalculator.model.Vehicle;
import com.volvo.congestioncalculator.utility.TaxCalculator;

@SpringBootTest
class VolvoApplicationTests {
	
	@Autowired
	private CongestionTaxController congestionTaxController;
	
	@Autowired
	private TaxCalculator taxCalculator;

	@Test
	void contextLoads() throws Exception {
		assertThat(congestionTaxController).isNotNull();
	}

	@Test
	void testTaxCalculation() {
		
		Vehicle vec=new Vehicle();
		vec.setVehicleSubType("sedan car");
		vec.setVehicleType("Car");
		
		String[] dates={"2013-01-14 08:00:00","2013-09-13 09:59:00"};
		int tax=taxCalculator.getGothenTax(vec, dates);
		
		assertThat(tax).isEqualTo(21);
		
	}
	
}
