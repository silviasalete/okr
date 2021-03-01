package com.okr.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory { 
	
	String 	    dbDriver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost/okr?userTimezone=true&serverTimezone=UTC";
    String 	    userName = "root";
    String 	    password = "170292sa";

	public Connection  getConnection() {
		
	      try {
	    	  Class.forName(dbDriver).newInstance();
			return DriverManager.getConnection(connectionUrl,userName, password);
			
		} catch (SQLException e) { 
			
			e.printStackTrace();			
			return null;
			
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
			
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
			return null;
			
		} catch (ClassNotFoundException e) { 
			
			e.printStackTrace();
			return null;
			
		}
	  }
}
