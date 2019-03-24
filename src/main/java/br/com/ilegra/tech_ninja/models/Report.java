package br.com.ilegra.tech_ninja.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Report {

	private File file;
	
	private List<Customer> customers = new ArrayList<Customer>();
	private List<Sale> sales = new ArrayList<Sale>();
	private List<Salesman> salesmen = new ArrayList<Salesman>();

	public Report(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public List<Salesman> getSalesmen() {
		return salesmen;
	}

	public void setSalesmen(List<Salesman> salesmen) {
		this.salesmen = salesmen;
	}
	
	public Long getAmountOfClients() {
		return this.customers.stream().map(Customer::getCnpj).distinct().count();
	}
	
	public Long getAmountOfSalesmen() {
		return this.salesmen.stream().map(Salesman::getCpf).distinct().count();
	}
	
	public String getMostExpensiveSale() {
		return this.sales.stream()
				.max((s1, s2) -> Double.compare(s1.getTotal(), s2.getTotal()))
				.get()
				.getSaleId();
	}
	
	private String getWorstSalesman() {
		return this.sales.stream()
				.min((s1, s2) -> Double.compare(s1.getTotal(), s2.getTotal()))
				.get()
				.getSalesmanName();
	}

	@Override
	public String toString() {
		String separator = "=============================================================================\n";
		
		String customers = separator + "Customers:\n";
		String salesmen = separator + "Salesmen:\n";
		String sales = separator + "Sales:\n";
		
		for (Customer c : this.customers) { customers += c.toString(); }
		for (Salesman s : this.salesmen) { salesmen += s.toString(); }
		for (Sale s : this.sales) { sales += s.toString(); }
		
		return "-----------------------------------------------------------------------------\n" + 
				"                                  Report:\n" + 
				"-----------------------------------------------------------------------------\n" +
				customers + "\n" +
				salesmen + "\n" +
				sales + "\n" + 
				separator +
				"Amount of clients:   " + getAmountOfClients() + "\n" +
				"Amount of salesman:  " + getAmountOfSalesmen() + "\n" +
				"Most expensive sale: id " + getMostExpensiveSale() + "\n" +
				"Worst salesman ever: " + getWorstSalesman();
	}

}
