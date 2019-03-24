package br.com.ilegra.tech_ninja.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ilegra.tech_ninja.exceptions.ConverterException;
import br.com.ilegra.tech_ninja.models.Customer;

public class CustomerConverter {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerConverter.class);

	public static Customer convertDataToCustomer(String pattern) throws ConverterException {
		log.info("Converting customer from pattern {}", pattern);
		
		Customer customer = null;
		if (pattern.startsWith("002")) {
			String[] data = pattern.split("ç");
			customer = new Customer(data[0], data[1], data[2], data[3]);
		}
		
		return customer;
	}
}
