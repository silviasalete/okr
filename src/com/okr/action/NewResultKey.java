package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.ResultKey;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class NewResultKey implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String 		idUser = request.getParameter("userId");
		String 	    idGoal = request.getParameter("idGoal");
		String description = request.getParameter("description");
		DataBase  dataBase = new DataBase();
		User 		  user = dataBase.getUserById(Integer.parseInt(idUser));		
		ResultKey resultKey = new ResultKey(description, Integer.parseInt(idGoal), user); 
		return dataBase.addListResultKey(resultKey)?"redirect:?action=Welcome":"redirect:?action=ResultKeyForm";
	}

}
