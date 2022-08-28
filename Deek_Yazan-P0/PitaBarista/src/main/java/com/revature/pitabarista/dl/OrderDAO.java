package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.models.LineItem;
import com.revature.pitabarista.models.Order;
import com.revature.pitabarista.models.Product;
import com.revature.pitabarista.models.StoreFront;
import com.revature.pitabarista.utils.ConnectionFactory;

public class OrderDAO {
//
//	public static void main(String[] args) {			
//		List<Order> ordersCustFour = new OrderDAO().getAll(4);			
//			for(int i = 0;i<ordersCustFour.size();i++) {
//				Order addedLineItems = ordersCustFour.get(i);
//				int orderID = addedLineItems.getId();
//				List<LineItem> lis = new LineItemDAO().getAllByOrderId(orderID);
//				ordersCustFour.set(1, addedLineItems);
//				
//			
//					}
//			
//			System.out.println(ordersCustFour);
//			
//			
//				
//		}
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

		
		
	  public List<Order> getAll(int customerId){
		  List<Order> orders = new ArrayList<Order>();
		  
		  try(Connection conn = ConnectionFactory.getConnection()){
			  String query = "SELECT * FROM orders WHERE customer_id = "+customerId+";";
			  
			  Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery(query);
			  Order order = null;
			  while(rs.next()) {
				  order = new Order();
				  order.setCustomer_id(customerId);
				  order.setId(rs.getInt("id"));
				  order.setStorefront_id(rs.getInt("store_id"));
				  orders.add(order);
			  }
			  return orders;
		  } catch (SQLException e) {
			
			e.printStackTrace();
		}
		  return orders;
		  
		  
	  }

 
}
	