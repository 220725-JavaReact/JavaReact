package com.revature.pitabarista.dl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pitabarista.models.Product;
import com.revature.pitabarista.utils.ConnectionFactory;

public class ProductDAO {
     
//  =========get all products============== 
	public List<Product> getAllProducs(){
		List<Product> products = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from products";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				products.add(new Product(rs.getInt("id"), rs.getString("product_name"), rs.getDouble("price"), rs.getString("description")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return products;
	}
	
	

	//=========get product by id ================ 
	public Product getById(int id) {
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from products where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery()
;
			if(rs.next()) {
				return new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getDouble("price"), rs.getString("description"));
			}
			
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
	
	// ===========add product================ 
	
	public void addProduct(Product newProduct) {
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "insert into products (product_name, price, description) values (?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, newProduct.getProductName());
			pstmt.setDouble(2, newProduct.getPrice());
			pstmt.setString(3, newProduct.getDescription());
	
			pstmt.execute();
		} catch (SQLException e) {
			
			System.out.println("Something went wrong");
		
		}
	}
	// ===========Remove Product ================
	public void removeProduct(Product newProduct) {
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "delete from products where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); 
			pstmt.setInt(1, newProduct.getId());
			ResultSet rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	
	
	
}


