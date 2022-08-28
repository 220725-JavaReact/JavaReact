package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pitabarista.models.Customer;
import com.revature.pitabarista.models.Inventory;
import com.revature.pitabarista.models.Order;
import com.revature.pitabarista.models.Product;
import com.revature.pitabarista.models.StoreFront;
import com.revature.pitabarista.utils.ConnectionFactory;

public class InventoryDAO {
  
  

  // show all inventories 
    public List<Inventory> getAllInventories(int productId){
      List<Inventory> inventories = new ArrayList<Inventory>();
      
      try(Connection conn = ConnectionFactory.getConnection()){
        String query = "select * from inventories inner join products on product.id = inventories.product.id";
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        Inventory inventory = null;
        while(rs.next()) {
          inventory = new Inventory();
          inventory.getProductId();
          inventory.getStockQt();
         
          inventories.add(inventory);
        }
        return inventories;
      } catch (SQLException e) {
      
      e.printStackTrace();
    }
      return inventories;
    }

    public int getStockQuantity(int storeId, int productId){
      try(Connection conn = ConnectionFactory.getConnection()){
        String selectQuery = "select * from inventories where inventories.store_id = ? and inventories.product_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(selectQuery); 
        pstmt.setInt(1, storeId);
        pstmt.setInt(2, productId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next() == false){
          return 0;
        } 
        else{
          return rs.getInt("stock_quantity");
        }
      }
      catch (SQLException e) {
    	
        e.printStackTrace();
      }
      return 0;
    }
    
    
    // Replenish inventory 
    
    public int updateInventory(int storeId, int productId, int newQuantity) {
      
      try(Connection connection = ConnectionFactory.getConnection()){
        String selectQuery = "select * from inventories where inventories.store_id = ? and inventories.product_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(selectQuery); 
        pstmt.setInt(1, storeId);
        pstmt.setInt(2, productId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next() == false){
          String insertQuery = "insert INTO inventories (store_id, product_id, stock_quantity) VALUES (?, ?, ?)";
          pstmt = connection.prepareStatement(insertQuery); 
          pstmt.setInt(1, storeId);
          pstmt.setInt(2, productId);
          pstmt.setInt(3, newQuantity);
          pstmt.execute();
        }
        else{
          String query =  "update inventories set stock_quantity = ? where inventories.store_id = ? and inventories.product_id = ?";
          pstmt = connection.prepareStatement(query); 
          pstmt.setInt(1, newQuantity);
          pstmt.setInt(2, storeId);
          pstmt.setInt(3, productId);
          pstmt.executeUpdate();
        }

//        selectQuery = "select * from inventories where inventories.store_id = ? and inventories.product_id = ?";
//        pstmt = connection.prepareStatement(selectQuery); 
//        pstmt.setInt(1, storeFront.getId());
//        pstmt.setInt(2, product.getId());
//        rs = pstmt.executeQuery();
//        return rs.getInt("stock_quantity");
        
        return this.getStockQuantity(storeId, productId);

      } catch (SQLException e) {
          e.printStackTrace();
        System.out.println("SQLException");
        return 0;
      }
      
      
    }
}
    

