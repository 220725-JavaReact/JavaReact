package com.revature.pitabarista.models;

public class Product {
     private int id;
     private String productName;
     private double price;
     private String description;

     
     public Product() {
	
	}

     
     

	public Product(int id, String productName, double price, String description) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.description = description;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", description="
				+ description + "]";
	}
     
     
     
     
     
}
