package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.pitabarista.models.LineItem;
import com.revature.pitabarista.models.Product;
import com.revature.pitabarista.models.StoreFront;
import com.revature.pitabarista.utils.ConnectionFactory;

public class StoreItemDAO {
	
	public void addInstance(StoreFront storeFront, LineItem lineItem) {
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "insert into storefront_items(storefront_id, product_id, quantity) values (?, ?, ?);";
			PreparedStatement pstmt = connection.prepareStatement(query); 
			pstmt.setInt(1, storeFront.getId());
			pstmt.setInt(2, lineItem.getProduct().getId());
			pstmt.setInt(3, lineItem.getQuantity());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
			//e.printStackTrace();
		}		
	}

	public void updateInstance(LineItem lineItem, StoreFront storeFront) {
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "update storefront_items set quantity = ? where storefront_id = ? and product_id = ?;";
			PreparedStatement pstmt = connection.prepareStatement(query); 
			pstmt.setInt(1, lineItem.getQuantity());
			pstmt.setInt(2, storeFront.getId());
			pstmt.setInt(3, lineItem.getProduct().getId());
			pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Something went wrong");
		}
		
	}

	

	public void deleteInstance(Product product, StoreFront storeFront) {
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "delete from storefront_items where product_id = ? and storefront_id = ?;";
			PreparedStatement pstmt = connection.prepareStatement(query); 
			pstmt.setInt(1, product.getId());
			pstmt.setInt(2, storeFront.getId());
			pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Something went wrong");
		}
	}

	


}
