package net.codejava;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement; 
import java.sql.ResultSet;


public class FlowerManager {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/sampledbb";
		String username = "root";
		String password = "Noile!171";
		
		
		try {
			 //Update Flower
		    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
		 
		    String sql1= "UPDATE flowers SET image ='C:\\\\Desktop\\\\Flowers' WHERE name ='Rose' ";
		    Statement statement1 = connection.createStatement();
		   int rows = statement1.executeUpdate(sql1);
		   if(rows > 0) {
			   System.out.println("The flower has been updated!");
		   }
		    
		    //GetFlower
		    
		 try {   String sql = "SELECT * FROM flowers";
		    Statement statement = connection.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    
		    while(result.next()) {
		    	int flowerID = result.getInt(1);
		    	String name = result.getString(2);
		    	String image = result.getString(3);
		    	String description = result.getString(4);
		    	System.out.println(flowerID + ", " + name + ", " + image + ", " + description);
		    	
		    }
		 }finally {
		 }    		    		    		   
		    connection.close();
		    
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		
		
	}
		
}
