package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.models.StoreFront;
import com.revature.pitabarista.utils.ConnectionFactory;

public class StoreFrontDAO {
	
	
	
	// ========get store front my id =============
	public StoreFront getById(int id) {
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from storefronts where storefront_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); 
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new StoreFront(rs.getInt("id"), rs.getString("storeName"), rs.getString("storeAddress"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	
	//==========get all stores ========
	public ArrayList<StoreFront> getAllStores() {
		ArrayList<StoreFront> storeFronts = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from storefronts";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				storeFronts.add(new StoreFront(rs.getInt("store_id"), rs.getString("store_name"), rs.getString("store_address")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return storeFronts;
	}
	

}
