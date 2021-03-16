package com.okr.model.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory { 
	
	public DataSource dataSource;

	String 	    dbDriver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost/okr?userTimezone=true&serverTimezone=UTC";
    String 	    userName = "root";
    String 	    password = "170292sa";

	public ConnectionFactory() {
		
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try { comboPooledDataSource.setDriverClass(dbDriver); } catch (PropertyVetoException e) { e.printStackTrace(); }
		comboPooledDataSource.setJdbcUrl(connectionUrl);
		comboPooledDataSource.setUser(userName);
		comboPooledDataSource.setPassword(password);
		comboPooledDataSource.setMaxPoolSize(15);
		this.dataSource = comboPooledDataSource;
	}

	public Connection  getConnection() {
		
	      try {
			return this.dataSource.getConnection();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	  }
}
