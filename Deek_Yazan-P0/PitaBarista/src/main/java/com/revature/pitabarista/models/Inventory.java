package com.revature.pitabarista.models;

public class Inventory {
	private int id; 
	private int productId;
	private int stockQt;
	
	
	public Inventory() {
		super();
	}


	public Inventory(int id, int productId, int stockQt) {
		super();
		this.id = id;
		this.productId = productId;
		this.stockQt = stockQt;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getStockQt() {
		return stockQt;
	}


	public void setStockQt(int stockQt) {
		this.stockQt = stockQt;
	}


	@Override
	public String toString() {
		return "Inventory [id=" + id + ", productId=" + productId + ", stockQt=" + stockQt + "]";
	}
	
	
	
	
	
}
