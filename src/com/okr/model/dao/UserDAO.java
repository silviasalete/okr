package com.okr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.okr.model.bean.User;

public class UserDAO {
	
	private ConnectionFactory  factory = new ConnectionFactory();
	  
	public Integer addUser(User user) {

		int generate_key = 0;
		
		try (Connection connection = factory.getConnection();) {
			
			try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, email, password, company_id) VALUES (?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);) {
				
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, user.getPassword());
				preparedStatement.setInt(4, 0);
				preparedStatement.execute();		
				
				
				try(ResultSet 	resultSet = preparedStatement.getGeneratedKeys();) {
					while(resultSet.next()) {
						generate_key =  resultSet.getInt(1); 
					}
				}
				
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return generate_key;
		
	}
	
	public User getUserById(int id) {

		User user = null;

		try (Connection connection = new ConnectionFactory().getConnection();){
			
			try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");) {
				
				preparedStatement.setInt(1, id);
				preparedStatement.execute();			
				
				try (ResultSet resultSet = preparedStatement.getResultSet();){
					while(resultSet.next()) {
						
						user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"));
						
					}
				} 
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public User userExists(String email, String password) {
		
		User 		   	 user = new User();
		
		try (Connection connection = factory.getConnection();){
			try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");){
				
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				preparedStatement.execute();
				
				
				try (ResultSet resultSet = preparedStatement.getResultSet();){
					while(resultSet.next()) {
						
						user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"));
						
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if ((user.getEmail() != null) && (user.getPassword() != null)) {

			return user;
		}else {

			return null;
		}
		
	}
	
}
