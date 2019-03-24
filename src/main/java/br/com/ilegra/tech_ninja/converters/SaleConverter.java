package br.com.ilegra.tech_ninja.converters;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ilegra.tech_ninja.exceptions.ConverterException;
import br.com.ilegra.tech_ninja.models.Item;
import br.com.ilegra.tech_ninja.models.Sale;

public class SaleConverter {

	private static final Logger log = LoggerFactory.getLogger(SaleConverter.class);
	
	public static Sale convertDataToSale(String pattern) throws ConverterException {
		log.info("Converting sale from pattern {}", pattern);
		
		Sale sale = null;
		List<Item> itemList = new ArrayList<Item>();
		
		if (pattern.startsWith("003")) {
			String[] data = pattern.split("ç");
			
			if (data[2].startsWith("[")) {
				
				String sanitizedData = data[2].replace("[", "").replace("]", "");
				
				String itens[] = sanitizedData.split(",");
				
				for (String item : itens) {
					String[] itemArray = item.split("-");
					itemList.add(new Item(
							itemArray[0], 
							Integer.valueOf(itemArray[1]), 
							Double.valueOf(itemArray[2].replace(",", "."))));
				}
				
			}
			
			sale = new Sale(data[0], data[1], itemList, data[3]);
		}
		
		return sale;
	}
	
}
