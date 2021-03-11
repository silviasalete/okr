package com.okr.model.dao;

import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import com.okr.model.bean.Objective;
import com.okr.model.bean.KeyResult;
import com.okr.model.bean.User;

public class DataBase {

	private static List<User> 			listUser = new ArrayList<>();
	private static List<Objective> listObjective = new ArrayList<>();
	private 		  ConnectionFactory  factory = new ConnectionFactory();
	  
	public Integer addUser(User user) {

		Connection connection = factory.getConnection();
		int generate_key = 0;
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, email, password, company_id) VALUES (?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, 0);
			preparedStatement.execute();
			
			ResultSet 	resultSet = preparedStatement.getGeneratedKeys();
			
			while(resultSet.next()) {
				generate_key =  resultSet.getInt(1); 
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return generate_key;
		
	}

	public List<User> getListUser() {
		
		return listUser;
	}
	 
	public User userExists(String email, String password) {
		
		ResultSet 	resultSet = null;
		User 		   	 user = new User();
		Connection connection = factory.getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				
				user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"));
				
			}

			connection.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if ((user.getEmail() != null) && (user.getPassword() != null)) {

			return user;
		}else {

			return null;
		}
		
	}

	public List<Objective> getListObjective() {
		
		return listObjective;
	}

	public List<Objective> getListObjectiveByUser(User user) {
		
		List<Objective> listObjectiveReturn = new ArrayList<>();
		Connection 				 connection = new ConnectionFactory().getConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM objective WHERE createdById = ?");
			preparedStatement.setInt(1, user.getId());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {

				Objective objective = new Objective(resultSet.getInt("id"),resultSet.getString("context"),user);
				listObjectiveReturn.add(objective);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listObjectiveReturn;
	}

	public User getUserById(int id) {

		User user = null;
		Connection connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				
				user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return user;
	}

	public boolean addObjective(Objective objective) {
		
		Connection    connection = factory.getConnection();
		boolean createdObjective = false;
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String data = simpleDateFormat.format(date);

			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO objective (context, image, periodId, privacyId, startObjective, endObjective, createdIn, updatedIn,createdById) VALUES (?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
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
			ResultSet 	resultSet = preparedStatement.getGeneratedKeys();
			
			while(resultSet.next()) {
				Integer id =  resultSet.getInt(1);
				if (id != null) {
					createdObjective = true;
				}
			}
			connection.close();
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return createdObjective;
		
	}

	public Objective getObjectiveById(int id) {
		
		Objective  objective = null;	
		Connection connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM objective WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				User user = getUserById(resultSet.getInt("createdById"));
				objective = new Objective(resultSet.getInt("id"), resultSet.getString("context"),user);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return objective;
	}

	public boolean updateObjective(Objective objective) {
		
		Connection 	  connection = new ConnectionFactory().getConnection();
		Integer 	updatedLines = 0;
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE objective SET context = ? WHERE id = ?");
			preparedStatement.setString(1, objective.getDescription());
			preparedStatement.setInt(2, objective.getId());
			preparedStatement.execute();
			updatedLines = preparedStatement.getUpdateCount();
			System.out.println("Linhas alteradas: "+updatedLines);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
		
	}

	public boolean removeObjective(int idObjective, int idUser) { 
		
		Connection 	  connection = new ConnectionFactory().getConnection();
		Integer 	updatedLines = 0;
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM objective WHERE ID = ? AND createdById = ?");
			preparedStatement.setInt(1, idObjective);
			preparedStatement.setInt(2, idUser);
			preparedStatement.execute();
			updatedLines = preparedStatement.getUpdateCount();

			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
	}
	

	public boolean addListKeyResult(KeyResult keyResult) {

		Connection    connection = factory.getConnection();
		boolean createdKeyResult = false;
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String data = simpleDateFormat.format(date);

			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO keyresult (objective,verb,counter,typeCounter,noun,complement,period,controlPeriod,percentageReached,startKeyResult,endKeyResult,createdIn,updatedIn,createdById) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
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
			
			ResultSet 	resultSet = preparedStatement.getGeneratedKeys();
			
			while(resultSet.next()) {
				Integer id =  resultSet.getInt(1);
				if (id != null) {
					createdKeyResult = true;
				}
			}
			connection.close();
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return createdKeyResult;
		
	}
	
	public KeyResult getKeyResultById(int id) {
		
		KeyResult  keyResult = new KeyResult();
		Connection connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM keyresult WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				User user = getUserById(resultSet.getInt("createdById"));
				keyResult = new KeyResult(resultSet.getInt("id"), resultSet.getString("complement"), resultSet.getInt("objective"), user);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return keyResult;
		
	}

	public boolean updateKeyResult(KeyResult keyResult) {
		
		Connection 	  connection = new ConnectionFactory().getConnection();
		Integer 	updatedLines = 0;
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE keyresult SET complement = ? WHERE id = ? and objective = ? and createdById = ?");
			preparedStatement.setString(1, keyResult.getDescription());
			preparedStatement.setInt(2, keyResult.getId());
			preparedStatement.setInt(3, keyResult.getIdObjective());
			preparedStatement.setInt(4, keyResult.getUser().getId());
			preparedStatement.execute();
			updatedLines = preparedStatement.getUpdateCount();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
	}

	public boolean removeKeyResult(int idUser, int idObjective, int idKeuResult) {
		
		Connection 	  connection = new ConnectionFactory().getConnection();
		Integer 	updatedLines = 0;
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM keyresult WHERE ID = ? AND createdById = ? AND objective = ?");
			preparedStatement.setInt(1, idKeuResult);
			preparedStatement.setInt(2, idUser);
			preparedStatement.setInt(3, idObjective);
			preparedStatement.execute();
			updatedLines = preparedStatement.getUpdateCount();

			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
	}

	public List<KeyResult> getListKeyResultByObjectiveId(int idObjective, int idUser) {
		
		List<KeyResult> listKeyResult = new ArrayList<KeyResult>();		 
		Connection 		   connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM keyresult WHERE objective = ? and createdById = ?");
			preparedStatement.setInt(1, idObjective);
			preparedStatement.setInt(2, idUser);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				User user = getUserById(resultSet.getInt("createdById"));
				KeyResult keyResult = new KeyResult(resultSet.getInt("id"), resultSet.getString("complement"), resultSet.getInt("objective"),user);
				listKeyResult.add(keyResult); 
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listKeyResult;
	}

	public KeyResult getKeyResultByIdUserObjectiveKeyResult(int idUser, int idObjective, int idKeyResult) {
		
		KeyResult  keyResult = new KeyResult();
		Connection connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM keyresult WHERE id = ? and objective = ? and createdById = ?");
			preparedStatement.setInt(1, idKeyResult);
			preparedStatement.setInt(2, idObjective);
			preparedStatement.setInt(3, idUser);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getResultSet();
			
			while(resultSet.next()) {
				User user = getUserById(resultSet.getInt("createdById"));
				keyResult = new KeyResult(resultSet.getInt("id"), resultSet.getString("complement"), resultSet.getInt("objective"), user);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return keyResult;
	}

}
