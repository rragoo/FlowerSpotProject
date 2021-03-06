package net.codejava;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.mysql.cj.jdbc.DatabaseMetaData;

import java.sql.ResultSet;


public class Sighting {
	
	
	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/sampledbb";
		String username = "root";
		String password = "Noile!171";
		
		String longitude = "longitude";
		String latitude = "latitude";
		String flower_reference = "flower_id";
		String image = "C:\\\\Desktop\\\\Flowers";
		
		
		try {
			 Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			
			 String sql = "INSERT INTO sightings (longitude, latitude, flower_reference, i)" + "VALUES(?,?,?)";
			 String getSqlQuery = "SELECT * FROM sightings INNER JOIN flowers ON sightings.flower_reference = flowers.flower_id";
			 PreparedStatement statement1 = connection.prepareStatement(getSqlQuery);
			 
			 ResultSet rsu = statement1.executeQuery(getSqlQuery);
			 
			 if(rsu.next()) {
	            	System.out.println("test");
	           
	            	rsu.getString(longitude);
	            	rsu.getString(latitude);
	            	rsu.getString(image);
	            	rsu.getString(flower_reference);
				 
			 }
			 // SELECT user.*, account.* FROM user INNER JOIN account ON user.id = account.user WHERE user.id = 1
			 PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			 
			 
			 statement.executeUpdate();
			 ResultSet rs = statement.getGeneratedKeys();
			 
			 statement.setString(1, longitude);
			 statement.setString(2, latitude);
			 statement.setString(3, image);
			 statement.setString(4, flower_reference);		 
			 int rows = statement.executeUpdate();				
			 
			 if(rows > 0) {
				 System.out.println("Inserted");
			 
			 DatabaseMetaData meta = (DatabaseMetaData) connection.getMetaData();
			 ResultSet test = meta.getTables(null, null, "sightings", new String[] {"sightings"} );
			 
			 try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	System.out.println("found");
		            	//user.setId(generatedKeys.getLong(1));
		            }
		            else {
		            	System.out.println("failed");
		                throw new SQLException("Creating user failed, no ID obtained.");
		            }
		        }
			 						
			 }
				connection.close();
			 
						
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

}
