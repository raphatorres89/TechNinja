package br.com.ilegra.tech_ninja.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ilegra.tech_ninja.converters.SalesmanConverter;
import br.com.ilegra.tech_ninja.exceptions.ConverterException;
import br.com.ilegra.tech_ninja.models.Salesman;

public class SalesmanConverterTest {

	@Test
	public void testConvertSalesmanFromPattern() throws ConverterException {
		Salesman salesman = SalesmanConverter
				.convertDataToSalesman("001ç1234567891234çDiegoç50000");
		
		assertEquals("001", salesman.getId());
		assertEquals("1234567891234", salesman.getCpf());
		assertEquals("Diego", salesman.getName());
		assertEquals(true, salesman.getSalary() == 50000);
	}

}
