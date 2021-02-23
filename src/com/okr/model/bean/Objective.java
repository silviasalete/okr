package com.okr.model.bean;

import java.util.ArrayList;
import java.util.List;

public class Objective {

	int 			id;
	String description;
	User 		  user;
	List<KeyResult> listKeyResult = new ArrayList<>();

	public Objective(String description, User user) {
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
	
	public List<KeyResult> getListKeyResult() {
		return listKeyResult;
	}

	public void setListKeyResult(List<KeyResult> listKeyResult) {
		this.listKeyResult = listKeyResult;
	}

	@Override
	public String toString() {
		return "Objective [id=" + id + ", description=" + description + ", user=" + user + ", listKeyResult="
				+ listKeyResult + "]";
	}

	
	
}
