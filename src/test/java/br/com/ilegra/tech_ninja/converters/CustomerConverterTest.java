package br.com.ilegra.tech_ninja.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ilegra.tech_ninja.converters.CustomerConverter;
import br.com.ilegra.tech_ninja.exceptions.ConverterException;
import br.com.ilegra.tech_ninja.models.Customer;

public class CustomerConverterTest {

	@Test
	public void testConvertCustomerFromPattern() throws ConverterException {
		Customer customer = CustomerConverter
				.convertDataToCustomer("002ç2345675434544345çJose da SilvaçRural");
		
		assertEquals("Rural", customer.getBusinessArea());
		assertEquals("2345675434544345", customer.getCnpj());
		assertEquals("Jose da Silva", customer.getName());
		assertEquals("002", customer.getId());
	}

}
