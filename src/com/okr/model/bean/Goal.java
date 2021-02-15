package com.okr.model.bean;

import java.util.ArrayList;
import java.util.List;

public class Goal {

	int 			id;
	String description;
	User 		  user;
	List<ResultKey> listResultKey = new ArrayList<>();

	public Goal(String description, User user) {
		super();
		this.id = (int) (Math.random() * 100);
		this.description = description;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<ResultKey> getListResultKey() {
		return listResultKey;
	}

	public void setListResultKey(List<ResultKey> listResultKey) {
		this.listResultKey = listResultKey;
	}

	@Override
	public String toString() {
		return "Goal [id=" + id + ", description=" + description + ", user=" + user + "]";
	}
	
}
