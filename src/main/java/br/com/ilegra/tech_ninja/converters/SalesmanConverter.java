package br.com.ilegra.tech_ninja.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ilegra.tech_ninja.exceptions.ConverterException;
import br.com.ilegra.tech_ninja.models.Salesman;

public class SalesmanConverter {
	
	private static final Logger log = LoggerFactory.getLogger(SalesmanConverter.class);

	public static Salesman convertDataToSalesman(String pattern) throws ConverterException {
		log.info("Converting salesman from pattern {}", pattern);
		
		Salesman salesman = null;
		
		if (pattern.startsWith("001")) {
			String[] data = pattern.split("ç");
			
			salesman = new Salesman(data[0], data[1], data[2], Double.valueOf(data[3].replace(",", ".")));
		}
		
		return salesman;
	}
	
}
