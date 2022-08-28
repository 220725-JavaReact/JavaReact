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
	
	//public static void main(String[] args) {
	//	StoreFront myStore = new StoreFrontDAO().getById(3);
	//	System.out.println(myStore);
	//}
	
	
	// ========get store front my id =============
	public StoreFront getById(int id) {
		StoreFront store = null;
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from storefronts where id = " + id + ";";
			Statement pstmt = connection.createStatement(); 
		
			ResultSet rs = pstmt.executeQuery(query);
			
			while(rs.next()) {
				store = new StoreFront(rs.getInt("id"), rs.getString("store_name"), rs.getString("store_address"));
				
			}
			
		} catch (SQLException e) {
	
		}
		return store;
	}
	
	//==========get all stores ========
	public ArrayList<StoreFront> getAllStores() {
		ArrayList<StoreFront> storeFronts = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from storefronts";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				storeFronts.add(new StoreFront(rs.getInt("id"), rs.getString("store_name"), rs.getString("store_address")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return storeFronts;
	}
	

}
