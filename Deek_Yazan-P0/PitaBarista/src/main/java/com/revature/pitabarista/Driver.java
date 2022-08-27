package com.revature.pitabarista;

import java.util.Scanner;

import com.revature.pitabarista.dl.CustomerDAO;
import com.revature.pitabarista.dl.EmployeeDAO;
import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.models.Employee;
import com.revature.pitabarista.ui.CustomerLogin;
import com.revature.pitabarista.ui.EmployeeLogin;

public class Driver {
	
	public static void main(String[] arga) {
	     CustomerDAO customerDao = new CustomerDAO();
	     EmployeeDAO employeeDao = new EmployeeDAO();
		 Scanner scan = new Scanner(System.in);
		 String userInput = "";
		
		
		System.out.println("Weclome to Pita Barista Cafe, your favorite local coffee shop here in Arizona");
		System.out.println("where you can get your morning going with our hot & cold drinks as well as our breakfast menu");
		
		while(!userInput.equals("x")) {
			System.out.println("Good morning sunshine, here is our store menu: ");
			System.out.println("What would you like to do today ? ");
			System.out.println("[1] Customer Sign in ");
			System.out.println("[2] Employee sign in ");
	        System.out.println("[3] New Cusomer Registration");            
	        System.out.println("[x] Exit Menu");
			
	
	        userInput = scan.nextLine();
			switch(userInput) {
			case "1":
				System.out.println("Hello please sign in here ");
				CustomerLogin.customerLogin(scan);
			break;
			case "2":
				System.out.println("Hello please sign in here ");
				EmployeeLogin.searchEmployee();
			     
			    
				break;
			case "3":
				Customer newCustomer = new Customer();
				System.out.println("Enter your first name: ");
				newCustomer.setFirstName(scan.nextLine());
				
				System.out.println("Enter your last naame: ");
				newCustomer.setLastName(scan.nextLine());
				System.out.println("Enter your email: ");
				newCustomer.setEmail(scan.nextLine());
				System.out.println("Enter your password: ");
				newCustomer.setPassword(scan.nextLine());
				
				customerDao.addCustomer(newCustomer);
				
				break; 
			case "x":
				System.out.println("Thanks for shopping!");
				break;
			default:
				System.out.println("Invalid Input");
				break; 
				
				
			
			
			}
			
			
			
			
		
		
			
			
		}
		
	}

}
