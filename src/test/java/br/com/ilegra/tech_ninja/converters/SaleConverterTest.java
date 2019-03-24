package br.com.ilegra.tech_ninja.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ilegra.tech_ninja.converters.SaleConverter;
import br.com.ilegra.tech_ninja.exceptions.ConverterException;
import br.com.ilegra.tech_ninja.models.Sale;


public class SaleConverterTest {
	
	@Test
	public void testConvertSaleFromPattern() throws ConverterException {
		Sale sale = SaleConverter
				.convertDataToSale("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego");
		
		assertEquals("003", sale.getId());
		assertEquals("10", sale.getSaleId());
		assertEquals("Diego", sale.getSalesmanName());
		assertEquals("1", sale.getItens().get(0).getId());
		assertEquals(true, sale.getItens().get(1).getPrice() == 2.50);
		assertEquals(true, sale.getItens().get(2).getQuantity() == 40);
		
	}

}
