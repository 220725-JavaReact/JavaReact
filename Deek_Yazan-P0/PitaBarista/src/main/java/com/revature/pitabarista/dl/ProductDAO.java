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
		Product product = null;
		try(Connection connection = ConnectionFactory.getConnection()){
			String query = "select * from products where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery()
;
			if(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				
			}
			
		} catch (SQLException e) {
			
		}
		return product;
	}
}

