package com.revature.pitabarista.ui;



import java.util.Scanner;

import com.revature.pitabarista.dl.CustomerDAO;

import com.revature.pitabarista.models.Customer;

public class CustomerLogin {
	
	public static Customer loginCustomer = null; 
	private static CustomerDAO login = new CustomerDAO();
	public static void customerLogin(Scanner scan) {
		
		
		boolean isActive = true;
		
		System.out.println("==========CUSTOMER LOGIN==============");
		
		while(isActive) {
			System.out.println("Enter your email: ");
			String email = scan.nextLine();
			
			System.out.println("Enter your password: ");
			String password = scan.nextLine();
			
			 loginCustomer = login.getCustomerByEmail(email);
			
			if(loginCustomer == null || !loginCustomer.getPassword().equals(password)) {
				System.out.println("Incorrect Email or Customer not found");
				isActive = false;
				break;
			} else {
				
				  StoreLocation.storeLocation(loginCustomer);
				//Shopping.shop(loginCustomer);
				
				
				
				
			}
			
			isActive = false;
		}
		
		
	}
	   


}