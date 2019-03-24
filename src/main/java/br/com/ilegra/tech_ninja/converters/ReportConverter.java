package br.com.ilegra.tech_ninja.converters;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ilegra.tech_ninja.exceptions.ConverterException;
import br.com.ilegra.tech_ninja.models.Report;
import br.com.ilegra.tech_ninja.utils.StringUtils;

public class ReportConverter {

	private static final Logger log = LoggerFactory.getLogger(ReportConverter.class);
	
	public static Report convertDataToReport(String pattern, File file) {
		log.info("Converting data from pattern {}", pattern);
		
		Report report = new Report(file);
		
		pattern = StringUtils.cleanPattern(pattern);
		
		String[] splittedReport = pattern.split(" 0");
		
		try {
			
			for (String splittedPart : splittedReport) {
				if (!splittedPart.startsWith("00")) {
					splittedPart = "0" + splittedPart;
				}
				
				switch (splittedPart.substring(0, 3)) {
				case "001":
					report.getSalesmen().add(SalesmanConverter.convertDataToSalesman(splittedPart));
					break;
				case "002":
					report.getCustomers().add(CustomerConverter.convertDataToCustomer(splittedPart));
					break;
				case "003":
					report.getSales().add(SaleConverter.convertDataToSale(splittedPart));
					break;
				default:
					break;
				}
			}
		} catch (ConverterException e) {
			log.error("Error converting data");
			e.printStackTrace();
		}
		return report;
	}
	
}
