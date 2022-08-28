package com.revature.pitabarista.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.pitabarista.dl.CustomerDAO;
import com.revature.pitabarista.dl.InventoryDAO;
import com.revature.pitabarista.dl.OrderDAO;
import com.revature.pitabarista.dl.ProductDAO;
import com.revature.pitabarista.dl.StoreFrontDAO;
import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.models.LineItem;
import com.revature.pitabarista.models.Order;
import com.revature.pitabarista.models.Product;
import com.revature.pitabarista.models.StoreFront;

public class Shopping {
	    private static final Scanner scanner = new Scanner(System.in);
	    private static final CustomerDAO customerDao = new CustomerDAO();
	    private static final ProductDAO productDao = new ProductDAO();
	    private static final OrderDAO orderDao = new OrderDAO();
	    private static final StoreFrontDAO storeFrontDao =  new StoreFrontDAO();
	    private static  StoreFront store;
	    private static final InventoryDAO inventoryDao = new InventoryDAO();
	    

	  static void shop(Customer shoppingCustomer, StoreFront s) {
		  store = s;
		  String userInput ="";
	
		  // Search for customer to shop for
	  
	       System.out.println("Welcome " +shoppingCustomer.getFirstName());

	        // cart represents the products we want to buy
	      
	        // instead of just products
	        List<LineItem> cart = new ArrayList<>();
	        // inventory represents the products in our DB that is on sale
	        
	        List<Product> inventory = productDao.getAllProducs();
	        // userInput represents the action user chooses
	       
	        while (userInput != "checkout") {
	            // show products
	            // based off of the indexing of lists wherein the first element has an index of
	            // 0
	            int index = 0;
	            for (Product product : inventory) {
	            	int stockQt = inventoryDao.getStockQuantity(store.getId(), product.getId());
	                // use index to identify the location of a product object in the list
	                System.out.println(index + " " + product.getProductName() + "\t" + product.getPrice() + "\t" + product.getDescription() + "\t" + stockQt + "items available" );
	                index++;
	            }
	            
	            // TODO edit this out to also ask the end user for quantity
	            System.out.println("Would you like to add a product to cart?");
	          
	            System.out.println("Enter index # if yes if not enter -1: ");
	            // add products to cart
	            userInput = scanner.nextLine();
	            try {
	                // with the end user's chosen product, use it's index to indentify which
	                // product to add to cart
	                int productIndex = Integer.parseInt(userInput);
	                if (productIndex > -1) {
	                Product add2Cart = inventory.get(productIndex);
	               
	                System.out.println("How many would you like of this");
	                int quantity = Integer.parseInt(scanner.nextLine());
	                  
	                 LineItem lineItem = new LineItem();
	                 lineItem.setQuantity(quantity);
	                 lineItem.setProduct(add2Cart);
	                 cart.add(lineItem);
	            }
	            else
	                    userInput = "checkout";
	            } catch (NumberFormatException ex) {
	                ex.printStackTrace();
	            } catch (IndexOutOfBoundsException ex) {
	                System.out.println("Product out of bounds");
	            }
	        }
	        // variable to store orderTotal
	        double total = 0;
	        for (LineItem lineItem : cart) {
	        	Product product = lineItem.getProduct();
	            System.out.println(lineItem.getProduct().getProductName() + "\t" + lineItem.getQuantity());
	            total += product.getPrice() * lineItem.getQuantity();
	        }
	        // print total
	        System.out.println("Total: " + total);
	        // checkout the products
	        Order newOrder = new Order();
	        newOrder.setCustomer_id(shoppingCustomer.getId());
	        newOrder.setTotal(total);
	        newOrder.setLineItems(cart);
	        
	       orderDao.addOrder(newOrder);
	       for (LineItem lineItem : cart) {
	    	   int actualStockQuantity = inventoryDao.getStockQuantity(store.getId(), lineItem.getProduct().getId());
	    	   int customerQuantity = lineItem.getQuantity();
	    	   int newQuantity = actualStockQuantity - customerQuantity;
	    	   inventoryDao.updateInventory(store.getId(), lineItem.getProduct().getId(), newQuantity);
	    	}

	    }
	  private static void showProducts() {
	        System.out.println("List of products");
	        // create a product DAO object

	        // productDao.getAllProducts returns a list of products
	        // "catching" the list of products the DAO is returning
	        List<Product> productsFromDB = productDao.getAllProducs();
	        // foreach loop => perfect for iterating over a list
	        for (Product product : productsFromDB) {
	            System.out.println(product);
	        }
	         

	    }


}
