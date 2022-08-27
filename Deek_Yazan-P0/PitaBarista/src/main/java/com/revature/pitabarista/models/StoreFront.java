package com.revature.pitabarista.models;

public class StoreFront {
	
	private int id;
	private String storeName;
	private String storeAddress;


	
	public StoreFront() {
		
	}


	public StoreFront(int id, String storeName, String storeAddress) {
		
		this.id = id;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getStoreAddress() {
		return storeAddress;
	}


	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}


	@Override
	public String toString() {
		return "StoreFront [id=" + id + ", storeName=" + storeName + ", storeAddress=" + storeAddress + "]";
	}
	
	
}
