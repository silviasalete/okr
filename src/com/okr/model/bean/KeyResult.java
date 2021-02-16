package com.okr.model.bean;

public class KeyResult {

	int id;
	String description;
	int idObjective;
	User user;

	public KeyResult(String description, int idObjective, User user) {
		super();
		this.id = (int) (Math.random()*100);
		this.description = description;
		this.idObjective = idObjective;
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
	public int getIdObjective() {
		return idObjective;
	}
	public void setIdObjective(int idObjective) {
		this.idObjective = idObjective;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "KeyResult [id=" + id + ", description=" + description + ", idObjective=" + idObjective + ", user=" + user + "]";
	}
}
