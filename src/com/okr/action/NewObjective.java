package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.Objective;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class NewObjective implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  description = request.getParameter("description");
		String       userId = request.getParameter("userId");
		DataBase   dataBase = new DataBase();
		User 		   user = dataBase.getUserById(Integer.parseInt(userId));
		Objective 		   objective = new Objective(description, user);
		boolean objectiveCreated = dataBase.addListObjective(objective);
		
		String retorno = objectiveCreated?"redirect:?action=Welcome":"redirect:?action=ObjectiveForm";
		return retorno;
		 
	}

}
