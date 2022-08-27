package com.revature.pitabarista.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.pitabarista.dl.StoreFrontDAO;
import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.models.StoreFront;

public class StoreLocation {
	// create a storefront DAO object
	private static final StoreFrontDAO storeFrontDao = new StoreFrontDAO();
	private static final Scanner scanner = new Scanner(System.in);
	
	public static void storeLocation () {
	   Customer customer = new Customer();
	   
	   boolean isActive = true; 
	   
	   while(isActive ) {
		   System.out.println("===========SELECT STORE LOCATION==============");
		   System.out.println("Login Succcees! Welcone, " + customer.getFirstName());
		   System.out.println("Please select form the follwoing: ");
		   System.out.println("[1] Select Store Location");
		   System.out.println("[2] View Order History");
		   System.out.println("[x] Quit");
		   
		   
		   // sdkjfsjf kjsfkljsdkfj sldkfj sdlf 
		   String userInput = scanner.nextLine();
		   switch(userInput){
		   case "1":
			  System.out.println("List of Stores: ");
			  StoreFrontDAO storefrontDao = new StoreFrontDAO();
			  List<StoreFront> storefrontsFromDB = storefrontDao.getAllStores();
			  
			  for(StoreFront storeFront: storefrontsFromDB) {
				   System.out.println(storeFront);
			  }
			  System.out.println("Please Enter # Select Store locaion: ");
			  userInput = scanner.nextLine();
			  try {
				  int storeIndex = Integer.parseInt(userInput);
				  if(storeIndex > -1){
					StoreFront chosenStoreFront = storefrontsFromDB.get(storeIndex);
					 
				  } else {
					  
				  }
					  
				  
				  
			  } catch (NumberFormatException ex) {
				  ex.printStackTrace();
			  }
			  break;
		   
		   case "2":
			   // view order history
			   
		   case "x":
		   		System.out.println("Leaving the store ......");
		   		isActive = false; 
		   }
		   
		   
	   }
	}
	}


