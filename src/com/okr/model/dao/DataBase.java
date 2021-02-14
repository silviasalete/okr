package com.okr.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.okr.model.bean.User;

public class DataBase {

	private static List<User> listUser = new ArrayList<>();
	
	static { 
		listUser.add(new User("Test","test@test.com","123"));
		listUser.add(new User("A","a@a.com","321"));
	}
	
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
	
}
