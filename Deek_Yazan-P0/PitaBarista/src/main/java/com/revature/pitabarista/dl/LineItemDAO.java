package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pitabarista.models.LineItem;
import com.revature.pitabarista.models.Order;
import com.revature.pitabarista.models.Product;
import com.revature.pitabarista.models.StoreFront;
import com.revature.pitabarista.utils.ConnectionFactory;

public class LineItemDAO {

//	public static void main(String[] args) {
//		List<LineItem> lis = new LineItemDAO().getAllByOrderId(5);
//		System.out.println(lis);
//	}
//	
	public ArrayList<LineItem> getAllByOrderId(int orderID){
		ProductDAO productDao = new ProductDAO();
		ArrayList<LineItem> lineItems = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from line_items where order_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); 
			pstmt.setInt(1,orderID);
			ResultSet rs = pstmt.executeQuery();			
			while(rs.next()) {
				LineItem li = new LineItem();
				li.setId(rs.getInt("id"));
				li.setProductId(rs.getInt("product_id"));
				li.setQuantity(rs.getInt("quantity"));
			
				lineItems.add(li);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return lineItems;
	}
	
public int getProductQuantityByStoreId(StoreFront storeFront, Product product) {
		
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from products\r\n"
					+ "	inner join storefront_items where storefront_id = 1 and product_id = 2;";
			PreparedStatement pstmt = connection.prepareStatement(query); 
			pstmt.setInt(1, storeFront.getId());
			pstmt.setInt(2, product.getId());
			ResultSet rs = pstmt.executeQuery();
			return rs.getInt("quantity");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return (Integer) null;
		}
		
		
	}



public void addInstanceByOrder(LineItem newInstance, Order order) {
	try(Connection connection = ConnectionFactory.getConnection()){
		String query = "insert into order_items (order_id, product_id, quanity) values (?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, order.getId());
		pstmt.setInt(2, newInstance.getProduct().getId());
		pstmt.setInt(3, newInstance.getQuantity());
		pstmt.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("oppss something is wrong");
		//e.printStackTrace();
	}
}
}
