package com.revature.pitabarista.utils;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ConnectionFactory {
 
    private static Connection connection;

  
    static {
   
        try {
            System.out.println("Loading driver we are in ConnectionFactory");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

  
    public static Connection getConnection() {
        System.out.println("executing get connection method in class ConnectionFactory");
     
        try {
            if (connection == null || connection.isClosed()) {
            
                String db_url = "jdbc:postgresql://javabeans.c7847w4sopom.us-west-1.rds.amazonaws.com:5432/postgres";
                String db_user = "yazan28";
                String db_password = "coding123";
                try {
                  
                    connection = DriverManager.getConnection(db_url, db_user, db_password);
                    System.out.println("finishing executiong of get connection");
                    return connection;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}