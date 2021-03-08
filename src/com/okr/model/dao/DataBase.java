package com.okr.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO user (name, email, password, company_id) VALUES ('"+user.getName()+"','"+user.getEmail()+"','"+user.getPassword()+"',0);",Statement.RETURN_GENERATED_KEYS);
			ResultSet 	resultSet = statement.getGeneratedKeys();
			
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
			Statement statement = connection.createStatement();
			String 			sql = "SELECT * FROM user WHERE email = '"+email+"' AND password = '"+password+"';";	
			statement.execute(sql);
					  resultSet = statement.getResultSet();
			
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
		String 							sql = "SELECT * FROM objective WHERE createdById = "+user.getId();		
		Connection 				 connection = new ConnectionFactory().getConnection();
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			
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
		
		String sql = "SELECT * FROM user WHERE id = "+id;

		User user = null;
		Connection connection = new ConnectionFactory().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			
			while(resultSet.next()) {
				
				user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return user;
	}

	public boolean addObjective(Objective objective) {
		
		Connection connection = factory.getConnection();
		boolean createdObjective = false;
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String data = simpleDateFormat.format(date);

			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO objective (context, image, periodId, privacyId, startObjective, endObjective, createdIn, updatedIn,createdById) VALUES ('"+objective.getDescription()+"','image',1,'PRIVATE','2021-03-01','2022-03-01','"+data+"','"+data+"',"+objective.getUser().getId()+");",Statement.RETURN_GENERATED_KEYS);
			ResultSet 	resultSet = statement.getGeneratedKeys();
			
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
		String 			 sql = "SELECT * FROM objective WHERE id = "+id;
		Connection connection = new ConnectionFactory().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			
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
		String 				 sql = "UPDATE objective SET context = '"+objective.getDescription()+"' WHERE id = "+objective.getId();
		Integer 	updatedLines = 0;
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			updatedLines = statement.getUpdateCount();
			System.out.println("Linhas alteradas: "+updatedLines);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
		
	}

	public boolean removeObjective(int idObjective, int idUser) { 
		
		Connection 	  connection = new ConnectionFactory().getConnection();		
		String 				 sql = "DELETE FROM objective WHERE ID = "+idObjective+" AND createdById = "+idUser;
		Integer 	updatedLines = 0;
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			updatedLines = statement.getUpdateCount();

			
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

			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO keyresult (objective,verb,counter,typeCounter,noun,complement,period,controlPeriod,percentageReached,startKeyResult,endKeyResult,createdIn,updatedIn,createdById) VALUES "
					+ "("+keyResult.getIdObjective()+",'fazer',1,'numero','mensagens','"+keyResult.getDescription()+"',30,30,0,'"+data+"','"+data+"','"+data+"','"+data+"',"+keyResult.getUser().getId()+");",Statement.RETURN_GENERATED_KEYS);
			ResultSet 	resultSet = statement.getGeneratedKeys();
			
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
		String 			 sql = "SELECT * FROM keyresult WHERE id = "+id;
		Connection connection = new ConnectionFactory().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			
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
		String 				 sql = "UPDATE keyresult SET complement = '"+keyResult.getDescription()+"' WHERE id = "+keyResult.getId()+" and objective = "+keyResult.getIdObjective()+" and createdById = "+keyResult.getUser().getId();
		Integer 	updatedLines = 0;
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			updatedLines = statement.getUpdateCount();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
	}

	public boolean removeKeyResult(int idUser, int idObjective, int idKeuResult) {
		
		Connection 	  connection = new ConnectionFactory().getConnection();		
		String 				 sql = "DELETE FROM keyresult WHERE ID = "+idKeuResult+" AND createdById = "+idUser+" AND objective = "+idObjective;
		Integer 	updatedLines = 0;
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			updatedLines = statement.getUpdateCount();

			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return updatedLines > 0 ? true : false;
	}

	public List<KeyResult> getListKeyResultByObjectiveId(int idObjective, int idUser) {
		
		List<KeyResult> listKeyResult = new ArrayList<KeyResult>();		
		String 			 		  sql = "SELECT * FROM keyresult WHERE objective = "+idObjective+" and createdById = "+idUser;
		Connection 		   connection = new ConnectionFactory().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			
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
		String 			 sql = "SELECT * FROM keyresult WHERE id = "+idKeyResult+"and objective = "+idObjective+" and createdById = "+idUser;
		Connection connection = new ConnectionFactory().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			ResultSet resultSet = statement.getResultSet();
			
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
