package com.mindteck.broscius.varialibrorum.data.form;

public class ProductForm {
	private String name;
	private String description;
	private double price;
	private int numberInStock;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberInStock() {
		return numberInStock;
	}

	public void setNumberInStock(int numberInStock) {
		this.numberInStock = numberInStock;
	}

	@Override
	public String toString() {
		return "ProductForm [name=" + name + ", description=" + description + ", price=" + price + ", numberInStock="
				+ numberInStock + "]";
	}

}
