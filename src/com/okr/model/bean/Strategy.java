package com.okr.model.bean;

public class Strategy {

	int			    id;
	String description;
	
	public Strategy(String description) {
		super();
		this.id = (int) (Math.random() * 100);
		this.description = description;
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
	
	@Override
	public String toString() {
		return "Strategy [id=" + id + ", description=" + description + "]";
	}
}
