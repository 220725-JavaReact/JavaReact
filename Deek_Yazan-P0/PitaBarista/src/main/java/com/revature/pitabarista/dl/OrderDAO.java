package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.models.LineItem;
import com.revature.pitabarista.models.Order;
import com.revature.pitabarista.models.Product;
import com.revature.pitabarista.utils.ConnectionFactory;

public class OrderDAO {

	  public void addOrder(Order order) {
	        // insert into orders (customer_id, total_price)
	        // values (1, 14.97) returning id;
	        try (Connection connection = ConnectionFactory.getConnection()) {
	            String query = "insert into orders (customer_id, total_price) values (?,?) returning id";
	            PreparedStatement pstmt = connection.prepareStatement(query);
	            // we have and Order object called order
	            // we know that the Order class has a customerId field
	            // how do we get the customer id field of my order object?
	            pstmt.setInt(1, order.getCustomer_id());
	            pstmt.setDouble(2, order.getTotal());

	            ResultSet rs = pstmt.executeQuery();
	            rs.next();
	            int order_id = rs.getInt(1);
	            // insert into line_items (product_id, order_id, quantity)
	            // values (1,1,2), (3,1,1);
	            for (LineItem lineItem : order.getLineItems()) {
	            	Product product = lineItem.getProduct();
	                query = "insert into line_items (product_id, order_id, quantity) values (?,?,?)";
	                pstmt = connection.prepareStatement(query);
	                // setting the product id of the product we wanted to buy
	                pstmt.setInt(1, product.getId());
	                // setting the order id
	                pstmt.setInt(2, order_id);
	                // setting the quantity, because our line items have no quantity yet, we default
	                // to 1
	                // TODO edit this default 1 value to the actual quantity that the end user would
	                // like to purchase
	                pstmt.setInt(3, lineItem.getQuantity());
	                pstmt.executeUpdate();
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	    }
	}
		
		
 
	
	
	
	
	
	
	