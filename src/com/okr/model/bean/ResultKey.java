package com.okr.model.bean;

public class ResultKey {

	int id;
	String description;
	int idGoal;
	User user;

	public ResultKey(String description, int idGoal, User user) {
		super();
		this.id = (int) (Math.random()*100);
		this.description = description;
		this.idGoal = idGoal;
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
	public int getIdGoal() {
		return idGoal;
	}
	public void setIdGoal(int idGoal) {
		this.idGoal = idGoal;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ResultKey [id=" + id + ", description=" + description + ", idGoal=" + idGoal + ", user=" + user + "]";
	}
}
