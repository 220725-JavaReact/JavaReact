package com.revature.pitabarista.models;
import com.revature.pitabarista.dl.ProductDAO;
public class LineItem {
       private int id;
       private Product product;
       private int quantity;
	private int productID;
	
       
       
       public LineItem(int id, Product product, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}



	public LineItem() {
		super();
	}



	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "LineItem [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}



	public void setProductId(int productID) {
		
		this.product = new ProductDAO().getById(productID);
		this.productID = product.getId();
		
	}



	public int getProductID() {
		if(productID == 0) {
			product = new ProductDAO().getById(productID);
			productID = product.getId();
		}
		return productID;         
	} 
       
       
       
      


	
       
       
}
