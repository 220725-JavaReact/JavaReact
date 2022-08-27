package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pitabarista.models.LineItem;
import com.revature.pitabarista.models.Order;
import com.revature.pitabarista.models.Product;
import com.revature.pitabarista.models.StoreFront;
import com.revature.pitabarista.utils.ConnectionFactory;

public class LineItemDAO {

	
	public ArrayList<LineItem> getAllByOrderId(Order order){
		ProductDAO productDao = new ProductDAO();
		ArrayList<LineItem> lineItems = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from order_items where order_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); 
			pstmt.setInt(1, order.getId());
			ResultSet rs = pstmt.executeQuery();			
			while(rs.next()) {
				lineItems.add(new LineItem(0, productDao.getById(rs.getInt("product_id")), order, rs.getInt("quantity")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
