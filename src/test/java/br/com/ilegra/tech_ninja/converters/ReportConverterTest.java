package br.com.ilegra.tech_ninja.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ilegra.tech_ninja.converters.ReportConverter;
import br.com.ilegra.tech_ninja.models.Report;

public class ReportConverterTest {

	@Test
	public void testConvertReportFromPattern() {
		Report report = ReportConverter
				.convertDataToReport("001ç1234567891234çDiegoç50000 001ç3245678865434çRenatoç40000.99 " + 
				"002ç2345675434544345çJose da SilvaçRural 002ç2345675433444345çEduardo PereiraçRural " + 
				"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego", null);
		
		assertEquals("Diego", report.getSalesmen().get(0).getName());
	}

}
