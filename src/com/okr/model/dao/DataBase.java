package com.okr.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.okr.model.bean.User;

public class DataBase {

	private static List<User> listUser = new ArrayList<>();
	
	static { 
		listUser.add(new User("test","test@test.com","123"));
	}
	
	public boolean addListUser(User user) {
		
		return listUser.add(user);
		
	}

	public List<User> getListUser() {
		return listUser;
	}
	
}
