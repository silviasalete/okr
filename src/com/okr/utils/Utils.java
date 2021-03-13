package com.okr.utils;

public class Utils {

	public String returnNameRequired(String fullName, int namePostition){

		String firstName = "";
		String lastName  = ""; 

		int idx = fullName.lastIndexOf(' ');
		
		if (idx == -1) {
			firstName = fullName;
		} else {
			firstName = fullName.substring(0, idx);
			lastName  = fullName.substring(idx + 1); 
		} 
		
		switch (namePostition) {
		case 1:
			return firstName;
			
		case 2:
			return lastName;

		default:
			return fullName;
		}
	}
}
