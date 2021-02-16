package com.okr.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.okr.model.bean.Objective;
import com.okr.model.bean.KeyResult;
import com.okr.model.bean.User;

import jdk.nashorn.internal.runtime.ListAdapter;

public class DataBase {

	private static List<User> listUser = new ArrayList<>();
	private static List<Objective> listObjective = new ArrayList<>();
	private static List<KeyResult> listKeyResult = new ArrayList<>();
	private static int sequencialKeyUser = 1;
	
	static { 
		
		User  userFirst = new User(sequencialKeyUser++,"Test","test@test.com","123");
		User usersecond = new User(sequencialKeyUser++,"A","a@a.com","321");
		
		listUser.add(userFirst);
		listUser.add(usersecond);
		
		Objective  objectiveFirst = new Objective("Concluir a formação Spring Framework da Alura", userFirst);
		Objective objectiveSecond = new Objective("Concluir a formação Anglular da Alura", usersecond);
		Objective  objectiveThird = new Objective("Concluir a formação Anglular da NodeJS", usersecond);
		
		listObjective.add(objectiveFirst);
		listObjective.add(objectiveSecond);
		listObjective.add(objectiveThird);
		
		KeyResult keyResultFirst = new KeyResult("Concluir 2 cursos de Servlet", objectiveFirst.getId(), objectiveFirst.getUser());
		
		listKeyResult.add(keyResultFirst);
		objectiveFirst.setListKeyResult(listKeyResult);
	}
	
//	====================== USER ========================
	public boolean addListUser(User user) {
		
		return listUser.add(user);
		
	}

	public List<User> getListUser() {
		return listUser;
	}
	
	public User userExists(String email, String password) {
		 
		User user = null;
		
		for (User itemUser : listUser) {
			
			if ((itemUser.getEmail().equals(email)) && (itemUser.getPassword().equals(password))) {

				user = itemUser;
			}
		} 
		
		return user;
	}
//	====================== OBJECTIVE ========================

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
	
	// =================================== KEY RESULT =================================

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
