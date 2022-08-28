package com.revature.pitabarista.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.pitabarista.dl.InventoryDAO;
import com.revature.pitabarista.dl.LineItemDAO;
import com.revature.pitabarista.dl.OrderDAO;
import com.revature.pitabarista.dl.StoreFrontDAO;
import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.models.Employee;
import com.revature.pitabarista.models.LineItem;
import com.revature.pitabarista.models.Order;
import com.revature.pitabarista.models.Person;
import com.revature.pitabarista.models.StoreFront;

public class StoreLocation {
  // create a storefront DAO object
  private static final StoreFrontDAO storeFrontDao = new StoreFrontDAO();
  private static final InventoryDAO inventoryDao = new InventoryDAO();

  private static final Scanner scanner = new Scanner(System.in);
  private static StoreFront chosenStoreFront;
  private static Customer customer;
  public static void storeLocation (Person person) {
    customer = (Customer) person;
   
     
     boolean isActive = true; 
     
     while(isActive ) {
       System.out.println("===========SELECT STORE LOCATION==============");
       System.out.println("Login Succcees! Welcome, " + customer.getFirstName());
       System.out.println("Please select form the follwoing: ");
       System.out.println("[1] Select Store Location");
       System.out.println("[2] View Order History");
       System.out.println("[3] Update your inventory");
       System.out.println("[x] Quit");
       
       
       String userInput = scanner.nextLine();
       switch(userInput){
       case "1":
        System.out.println("List of Stores: ");
       
        List<StoreFront> storefrontsFromDB = storeFrontDao.getAllStores();
        
        for(StoreFront storeFront: storefrontsFromDB) {
           System.out.println(storeFront);
        }
        System.out.println("Please Enter store's id number to select location:  ");
        userInput = scanner.nextLine();
        try {
          int storeId = Integer.parseInt(userInput);
          if(storeId > -1){
        chosenStoreFront = storeFrontDao.getById(storeId);
        if (chosenStoreFront != null) {
          Shopping.shop(customer, chosenStoreFront);
        }
        
          } else {
            
            System.out.println("Invalid Input! Try again!");
            
          }
            
          
          
        } catch (NumberFormatException ex) {
          ex.printStackTrace();
        }
        
        break;
       
       case "2":
         
        List<Order> ordersCust= new OrderDAO().getAll(customer.getId());      
        for(int i = 0;i<ordersCust.size();i++) {
          Order addedLineItems = ordersCust.get(i);
          int orderID = addedLineItems.getId();
          List<LineItem> lis = new LineItemDAO().getAllByOrderId(orderID);
          ordersCust.set(1, addedLineItems);
          
        
            }
        
        System.out.println(ordersCust);
        break;
        
       case "3":
         System.out.println("Please enter the store location id");
         userInput = scanner.nextLine();
         int storeId = Integer.parseInt(userInput);

         System.out.println("Please enter the product id");
         userInput = scanner.nextLine();
         int productId = Integer.parseInt(userInput);

         int actualQuantity = inventoryDao.getStockQuantity(storeId, productId);
         System.out.println("Current Quantity:" +actualQuantity + " Please enter the new quantity:");
         userInput = scanner.nextLine();
         int newQuantity = Integer.parseInt(userInput);
         inventoryDao.updateInventory(storeId, productId, newQuantity);
        System.out.println("The quantity is updated successfully to "+ newQuantity);
         break;
         
       case "x":
           System.out.println("Leaving the store ......");
           isActive = false; 
       }
       
       
     }
  }
  }


