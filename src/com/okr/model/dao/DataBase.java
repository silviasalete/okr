package com.okr.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List; 
import com.okr.model.bean.Objective;
import com.okr.model.bean.KeyResult;
import com.okr.model.bean.User;

public class DataBase {

	private static List<User> listUser = new ArrayList<>();
	private static List<Objective> listObjective = new ArrayList<>();
	private static List<KeyResult> listKeyResult = new ArrayList<>();
	private ConnectionFactory connectionFactory = new ConnectionFactory();
	  
	public boolean addListUser(User user) {
		
		return listUser.add(user);
		
	}

	public List<User> getListUser() {
		return listUser;
	}
	 
	public User userExists(String email, String password) {
		
		ResultSet 	resultSet = null;
		User 		   	 user = new User();
		
		try {
					 Connection connection = connectionFactory.getConnection();
			Statement statement = connection.createStatement();
			String 			sql = "SELECT * FROM user WHERE email = '"+email+"' AND password = '"+password+"';";	
			statement.execute(sql);
					  resultSet = statement.getResultSet();
			
			while(resultSet.next()) {
				
				user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"));
				
			}
			
//			connectionFactory.close();

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
	
		for (Objective objective : listObjective) {

			if (objective.getUser().equals(user)) {

				listObjectiveReturn.add(objective);
				
			}
		}

		return listObjectiveReturn;
	}

	public User getUserById(int id) {
		User user = null;
		for (User userItem : listUser) {
			if (userItem.getId() == id) {
				user = userItem;
			}
		}
		return user;
	}

	public boolean addListObjective(Objective objective) {
		
		return listObjective.add(objective);
		
	}

	public Objective getObjectiveById(int id) {
		Objective objective = null;
		for (Objective objectiveItem : listObjective) {
			if (objectiveItem.getId() == id) {
				objective = objectiveItem;
			}
		}
		return objective;
	}

	public boolean updateObjective(Objective objectiveParameter) {
		
		boolean objectiveUpdated = false;
		
		for (Objective objectiveItem : listObjective) {
			
			if (objectiveItem.getId() == objectiveParameter.getId()) {
				
				objectiveItem = objectiveParameter;
				objectiveUpdated = true;
			}
		}
		return objectiveUpdated ;
	}

	public boolean removeObjective(int idObjective, int idUser) {
		boolean removedObjective = false;
		Iterator<Objective> iterator = listObjective.iterator();
		
		while (iterator.hasNext()) {
			Objective objective = (Objective) iterator.next();
			if ((objective.getId() == idObjective) && (objective.getUser().getId() == idUser)) {
				iterator.remove();
				removedObjective = true;
			}
		}
		
		return removedObjective;
	}
	

	public boolean addListKeyResult(KeyResult keyResult) {

		boolean keyResultAdd = listKeyResult.add(keyResult);

		Objective objective = getObjectiveById(keyResult.getIdObjective());
		objective.setListKeyResult(listKeyResult);

		return keyResultAdd;
	}
	
	public KeyResult getKeyResultById(int id) {
		
		KeyResult keyResult = null;
		
		for (KeyResult keyResultItem : listKeyResult) {
			
			if (keyResultItem.getId() == id) {
				
				keyResult = keyResultItem;
			}
		}
		
		return keyResult;
	}

	public boolean updateKeyResult(KeyResult keyResult) {
		boolean updatedKeyResult = false;
		for (KeyResult keyResultItem : listKeyResult) {
			if (keyResultItem.getId() == keyResult.getId()) {
				keyResultItem = keyResult;
				updatedKeyResult = true;
			}
		}
		return updatedKeyResult;
	}

	public boolean removeKeyResult(int id, User user) {
		
		Iterator<KeyResult> iterator = listKeyResult.iterator();		
		boolean 	removedKeyResult = false;
		
		while(iterator.hasNext()) {

			KeyResult keyResult = (KeyResult) iterator.next();
			
			if ((keyResult.getId() == id) && (keyResult.getUser().getId() == user.getId())) {
				 iterator.remove();	
				removedKeyResult = true;
			}
		}
		return removedKeyResult ;
	}

}
