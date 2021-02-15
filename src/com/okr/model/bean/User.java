package com.okr.model.bean;

public class User {

	private    int id;
	private String name;
	private String email;
	private String password;

	public User() {
		super();
	}

	public User(int id, String name, String email, String password) {
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
	}	

	public User(String name, String email, String password) { 
		setId((int) (Math.random() * 100));
		setName(name);
		setEmail(email);
		setPassword(password);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
}
