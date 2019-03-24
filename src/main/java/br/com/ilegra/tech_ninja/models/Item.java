package br.com.ilegra.tech_ninja.models;

public class Item {

	private String id;
	private Integer quantity;
	private Double price;
	
	public Item() {}

	public Item(String id, Integer quantity, Double price) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getTotal() {
		return this.quantity * this.getPrice();
	}

	@Override
	public String toString() {
		return "        [id: " + id + ", quantity: " + quantity + ", price: " + price + "]\n";
	}

	
}
