package com.revature.pitabarista.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pitabarista.models.Employee;
import com.revature.pitabarista.utils.ConnectionFactory;

public class EmployeeDAO {
	
	 public Employee getEmployeeByEmail(String email) {
	        try (Connection connection = ConnectionFactory.getConnection()) {
	            String query = "select * from employees where email = ?";
	            PreparedStatement pstmt = connection.prepareStatement(query);
	            pstmt.setString(1, email);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                return new Employee(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"),
	                        rs.getString("emp_email"));
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }

	}
