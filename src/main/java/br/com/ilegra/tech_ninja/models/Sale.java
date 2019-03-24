package br.com.ilegra.tech_ninja.models;

import java.util.List;

public class Sale {

	private String id;
	private String saleId;
	private List<Item> itens;
	private String salesmanName;
	
	public Sale() {}
	
	public Sale(String id, String saleId, List<Item> itens, String salesmanName) {
		this.id = id;
		this.saleId = saleId;
		this.itens = itens;
		this.salesmanName = salesmanName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	
	public Double getTotal() {
		return this.itens.stream().mapToDouble(i -> i.getTotal()).sum();
	}

	@Override
	public String toString() {
		String itens = "\n    Itens:\n";
		for (Item i : this.itens) { itens += i.toString(); }
		
		return " - id: " + id + ", saleId: " + saleId + ", salesmanName: " + salesmanName + "," + itens + "\n";
	}

}
