package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.utils.ConnectionFactory;



public class CustomerDAO {
      
	//add customer 
	public void addCustomer(Customer newCustomer) {
	
	  try(Connection connection = ConnectionFactory.getConnection()){
		   
		  String query = "insert into customers (first_name, last_name, email, customer_password) values (?,?,?,?)";
		  
	      PreparedStatement pstmt = connection.prepareStatement(query);
		  
	      pstmt.setString(1, newCustomer.getFirstName());
          pstmt.setString(2, newCustomer.getLastName());
          pstmt.setString(3, newCustomer.getEmail());
          pstmt.setString(4, newCustomer.getPassword());

          // execute query
          pstmt.executeUpdate();
    	  
	  } catch (SQLException ex) {
		  ex.printStackTrace();
	  }
	}
	
	 // get customer by Email for login
	 public Customer getCustomerByEmail(String email) {
	        try (Connection connection = ConnectionFactory.getConnection()) {
	            String query = "select * from customers where email = ?";
	            PreparedStatement pstmt = connection.prepareStatement(query);
	            pstmt.setString(1, email);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
	                        rs.getString("email"), rs.getString("customer_password"));
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }

}
