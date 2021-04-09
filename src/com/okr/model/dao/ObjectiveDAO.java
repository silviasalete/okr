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
import com.okr.model.bean.Objective;
import com.okr.model.bean.User;

public class ObjectiveDAO {
	
	private ConnectionFactory  factory = new ConnectionFactory();
	UserDAO 				   userDAO = new UserDAO();
	KeyResultDAO 		  keyResultDAO = new KeyResultDAO();

	public boolean addObjective(Objective objective) {		
		
		boolean createdObjective = false;
		
		try (Connection    connection = factory.getConnection();){
			
			try (PreparedStatement preparedStatement = 
					connection.prepareStatement("INSERT INTO objective (context, image, periodId, privacyId, startObjective, endObjective, createdIn, updatedIn,createdById) VALUES (?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);) {
				
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String data = simpleDateFormat.format(date);

				preparedStatement.setString(1, objective.getDescription());
				preparedStatement.setString(2, "image");
				preparedStatement.setInt(3, 1);
				preparedStatement.setString(4, "PRIVATE");
				preparedStatement.setString(5, "2021-03-01");
				preparedStatement.setString(6, "2022-03-01");
				preparedStatement.setString(7, data);
				preparedStatement.setString(8, data);
				preparedStatement.setInt(9, objective.getUser().getId());
				preparedStatement.execute();
				
				
				try (ResultSet 	resultSet = preparedStatement.getGeneratedKeys();){
					while(resultSet.next()) {
						Integer id =  resultSet.getInt(1);
						if (id != null) {
							createdObjective = true;
						}
					}
				}
				
			} 
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return createdObjective;
		
	}
	
	public Objective getObjectiveById(int id) {
		
		Objective  objective = null;	
		
		try(Connection connection = new ConnectionFactory().getConnection();){
			
			try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM objective WHERE id = ?");){
				
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
				
				try(ResultSet resultSet = preparedStatement.getResultSet();){
					while(resultSet.next()) {
						User user = userDAO.getUserById(resultSet.getInt("createdById"));
						objective = new Objective(resultSet.getInt("id"), resultSet.getString("context"),user);
						
					}
				}
				
			}
		} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return objective;
	}
	
	public boolean updateObjective(Objective objective) {
		
		Integer 	updatedLines = 0;
		
		try(Connection 	  connection = new ConnectionFactory().getConnection();){
			
			try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE objective SET context = ? WHERE id = ?");){
				
				preparedStatement.setString(1, objective.getDescription());
				preparedStatement.setInt(2, objective.getId());
				preparedStatement.execute();
				updatedLines = preparedStatement.getUpdateCount();
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
		
	}

	public List<Objective> getListObjectiveByUser(User user) {
		
		List<Objective> listObjectiveReturn = new ArrayList<>();

		try (Connection connection = new ConnectionFactory().getConnection();) {
			
			try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM objective WHERE createdById = ?");){
				
				preparedStatement.setInt(1, user.getId());
				preparedStatement.execute();				
				
				try (ResultSet resultSet = preparedStatement.getResultSet();) {
					while(resultSet.next()) {

						Objective objective = new Objective(resultSet.getInt("id"),resultSet.getString("context"),user);
						listObjectiveReturn.add(objective);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listObjectiveReturn;
	}

	public boolean removeObjective(int idObjective, int idUser) { 
		
		Integer 	updatedLines = 0;
		
		try(Connection 	  connection = new ConnectionFactory().getConnection();){
			
			try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM objective WHERE ID = ? AND createdById = ?");){
				
				connection.setAutoCommit(false);
				
				preparedStatement.setInt(1, idObjective);
				preparedStatement.setInt(2, idUser);
				preparedStatement.execute();
				updatedLines = preparedStatement.getUpdateCount();
				
				List<KeyResult> listKeyResult = keyResultDAO.getListKeyResultByObjectiveId(idObjective, idUser);
				boolean deletedKeyResult = true;
				
				for (KeyResult keyResult : listKeyResult) {
					keyResultDAO.removeKeyResult(idUser, idObjective, keyResult.getId());
				}
				
				if (deletedKeyResult) {
					
					connection.commit();
					
				}else {
					connection.rollback();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return updatedLines > 0 ? true : false;
	}
	
}
