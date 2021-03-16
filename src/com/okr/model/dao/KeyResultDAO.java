package com.okr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.okr.model.bean.KeyResult;
import com.okr.model.bean.User;

public class KeyResultDAO {

	private ConnectionFactory  factory = new ConnectionFactory();
	UserDAO 				   userDAO = new UserDAO();
	
	public boolean addListKeyResult(KeyResult keyResult) {
		
		boolean createdKeyResult = false;
		
		try (Connection    connection = factory.getConnection();) {
			
			try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO keyresult (objective,verb,counter,typeCounter,noun,complement,period,controlPeriod,percentageReached,startKeyResult,endKeyResult,createdIn,updatedIn,createdById) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);) {
			
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String data = simpleDateFormat.format(date);
	
				preparedStatement.setInt(1, keyResult.getIdObjective());
				preparedStatement.setString(2, "fazer");
				preparedStatement.setInt(3, 1);
				preparedStatement.setString(4, "numero");
				preparedStatement.setString(5, "mensagens");
				preparedStatement.setString(6, keyResult.getDescription());
				preparedStatement.setInt(7, 30);
				preparedStatement.setInt(8, 30);
				preparedStatement.setInt(9, 0);
				preparedStatement.setString(10, data);
				preparedStatement.setString(11, data);
				preparedStatement.setString(12, data);
				preparedStatement.setString(13, data);
				preparedStatement.setInt(14, keyResult.getUser().getId());			
				preparedStatement.execute();
				
				try(ResultSet 	resultSet = preparedStatement.getGeneratedKeys();){
					while(resultSet.next()) {
						Integer id =  resultSet.getInt(1);
						if (id != null) {
							createdKeyResult = true;
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return createdKeyResult;
		
	}
	
	public KeyResult getKeyResultById(int id) {
		
		KeyResult  keyResult = new KeyResult();
		Connection connection = new ConnectionFactory().getConnection();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM keyresult WHERE id = ?");){
			
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
			try(ResultSet resultSet = preparedStatement.getResultSet();){
				
				while(resultSet.next()) {
					User user = userDAO.getUserById(resultSet.getInt("createdById"));
					keyResult = new KeyResult(resultSet.getInt("id"), resultSet.getString("complement"), resultSet.getInt("objective"), user);
					
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return keyResult;
		
	}
	
	public List<KeyResult> getListKeyResultByObjectiveId(int idObjective, int idUser) {
		
		List<KeyResult> listKeyResult = new ArrayList<KeyResult>();		 
		
		try(Connection 		   connection = new ConnectionFactory().getConnection();){
			try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM keyresult WHERE objective = ? and createdById = ?");){
				
				preparedStatement.setInt(1, idObjective);
				preparedStatement.setInt(2, idUser);
				preparedStatement.execute();
				
				try (ResultSet resultSet = preparedStatement.getResultSet();){
					while(resultSet.next()) {
						User user = userDAO.getUserById(resultSet.getInt("createdById"));
						KeyResult keyResult = new KeyResult(resultSet.getInt("id"), resultSet.getString("complement"), resultSet.getInt("objective"),user);
						listKeyResult.add(keyResult); 
					}
				}
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return listKeyResult;
	}
	
	public KeyResult getKeyResultByIdUserObjectiveKeyResult(int idUser, int idObjective, int idKeyResult) {
		
		KeyResult  keyResult = new KeyResult();
		
		try (Connection connection = new ConnectionFactory().getConnection();){
			
			try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM keyresult WHERE id = ? and objective = ? and createdById = ?");){
				
				preparedStatement.setInt(1, idKeyResult);
				preparedStatement.setInt(2, idObjective);
				preparedStatement.setInt(3, idUser);
				preparedStatement.execute();
				
				try (ResultSet resultSet = preparedStatement.getResultSet();) {
					
					while(resultSet.next()) {
						User user = userDAO.getUserById(resultSet.getInt("createdById"));
						keyResult = new KeyResult(resultSet.getInt("id"), resultSet.getString("complement"), resultSet.getInt("objective"), user);
						
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return keyResult;
	}
	
	public boolean updateKeyResult(KeyResult keyResult) {
		
		Integer 	updatedLines = 0;
		
		try (Connection 	  connection = new ConnectionFactory().getConnection();){
			try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE keyresult SET complement = ? WHERE id = ? and objective = ? and createdById = ?");) {
				
				preparedStatement.setString(1, keyResult.getDescription());
				preparedStatement.setInt(2, keyResult.getId());
				preparedStatement.setInt(3, keyResult.getIdObjective());
				preparedStatement.setInt(4, keyResult.getUser().getId());
				preparedStatement.execute();
				updatedLines = preparedStatement.getUpdateCount();
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
	}
	
	public boolean removeKeyResult(int idUser, int idObjective, int idKeuResult) {
		
		Integer 	updatedLines = 0;
		
		try (Connection 	  connection = new ConnectionFactory().getConnection();){
			
			try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM keyresult WHERE ID = ? AND createdById = ? AND objective = ?");){
				
				preparedStatement.setInt(1, idKeuResult);
				preparedStatement.setInt(2, idUser);
				preparedStatement.setInt(3, idObjective);
				preparedStatement.execute();
				updatedLines = preparedStatement.getUpdateCount();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
	}
}
