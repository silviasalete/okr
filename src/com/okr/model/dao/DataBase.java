package com.okr.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.okr.model.bean.Goal;
import com.okr.model.bean.ResultKey;
import com.okr.model.bean.User;

import jdk.nashorn.internal.runtime.ListAdapter;

public class DataBase {

	private static List<User> listUser = new ArrayList<>();
	private static List<Goal> listGoal = new ArrayList<>();
	private static List<ResultKey> listResultKey = new ArrayList<>();
	private static int sequencialKeyUser = 1;
	
	static { 
		
		User  userFirst = new User(sequencialKeyUser++,"Test","test@test.com","123");
		User usersecond = new User(sequencialKeyUser++,"A","a@a.com","321");
		
		listUser.add(userFirst);
		listUser.add(usersecond);
		
		Goal  goalFirst = new Goal("Concluir a formação Spring Framework da Alura", userFirst);
		Goal goalSecond = new Goal("Concluir a formação Anglular da Alura", usersecond);
		Goal  goalThird = new Goal("Concluir a formação Anglular da NodeJS", usersecond);
		
		listGoal.add(goalFirst);
		listGoal.add(goalSecond);
		listGoal.add(goalThird);
		
		ResultKey resultKeyFirst = new ResultKey("Concluir 2 cursos de Servlet", goalFirst.getId(), goalFirst.getUser());
		
		listResultKey.add(resultKeyFirst);
		goalFirst.setListResultKey(listResultKey);
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
//	====================== GOAL ========================

	public List<Goal> getListGoal() {
		return listGoal;
	}

	public List<Goal> getListGoalByUser(User user) {

		List<Goal> listGoalReturn = new ArrayList<>();
	
		for (Goal goal : listGoal) {

			if (goal.getUser().equals(user)) {

				listGoalReturn.add(goal);
				
			}
		}

		return listGoalReturn;
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

	public boolean addListGoal(Goal goal) {
		
		return listGoal.add(goal);
		
	}

	public Goal getGoalById(int id) {
		Goal goal = null;
		for (Goal goalItem : listGoal) {
			if (goalItem.getId() == id) {
				goal = goalItem;
			}
		}
		return goal;
	}

	public boolean updateGoal(Goal goalParameter) {
		
		boolean goalUpdated = false;
		
		for (Goal goalItem : listGoal) {
			
			if (goalItem.getId() == goalParameter.getId()) {
				
				goalItem = goalParameter;
				goalUpdated = true;
			}
		}
		return goalUpdated ;
	}

	public boolean removeGoal(int idGoal, int idUser) {
		boolean removedGoal = false;
		Iterator<Goal> iterator = listGoal.iterator();
		
		while (iterator.hasNext()) {
			Goal goal = (Goal) iterator.next();
			if ((goal.getId() == idGoal) && (goal.getUser().getId() == idUser)) {
				iterator.remove();
				removedGoal = true;
			}
		}
		
		return removedGoal;
	}

	public boolean addListResultKey(ResultKey resultKey) {

		boolean resultKeyAdd = listResultKey.add(resultKey);

		Goal goal = getGoalById(resultKey.getIdGoal());
		goal.setListResultKey(listResultKey);

		return resultKeyAdd;
	}

}
