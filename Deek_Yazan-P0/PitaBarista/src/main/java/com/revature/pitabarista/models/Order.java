package com.revature.pitabarista.models;

import java.util.List;

public class Order {
     private int id;
     // line item in arraylist 
     private int customer_id;
     private int storefront_id;
     private List<LineItem> lineItems;
     private double total;

     
     public Order() {
		
	}


	public Order(int id, int customer_id, int storefront_id, List<LineItem> lineItems, double total) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.storefront_id = storefront_id;
		this.lineItems = lineItems;
		this.total= total;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}


	public int getStorefront_id() {
		return storefront_id;
	}


	public void setStorefront_id(int storefront_id) {
		this.storefront_id = storefront_id;
	}


	public List<LineItem> getLineItems() {
		return lineItems;
	}


	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double totalPriceOfItems) {
		this.total = totalPriceOfItems;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_id=" + customer_id + ", storefront_id=" + storefront_id + ", lineItems="
				+ lineItems + ", total=" + total + "]";
	}


	
     
     
     
     
}