package com.revature.pitabarista.ui;

import java.util.Scanner;

import com.revature.pitabarista.dl.EmployeeDAO;
import com.revature.pitabarista.models.Employee;

public class EmployeeLogin {
	
	
	static Scanner scanner = new Scanner(System.in);
	static EmployeeDAO employeeDao = new EmployeeDAO();
	
	public static Employee searchEmployee() {
        // ask user for customer email
        System.out.println("Please enter email of employee: ");
        String email = scanner.nextLine();
        // call dao to get customer with matching email
        Employee filteredEmployee = employeeDao.getEmployeeByEmail(email);
        // logic for printing out customer info
        
        StoreLocation.storeLocation();
        if (filteredEmployee == null)
            System.out.println("No Employee found with that email :<");
        else {
            System.out.println(filteredEmployee);
        }
        return filteredEmployee;
        
     
    }
	
	   
}
