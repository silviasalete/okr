package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.Goal;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class NewGoal implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  description = request.getParameter("description");
		String       userId = request.getParameter("userId");
		DataBase   dataBase = new DataBase();
		User 		   user = dataBase.getUserById(Integer.parseInt(userId));
		Goal 		   goal = new Goal(description, user);
		boolean goalCreated = dataBase.addListGoal(goal);
		
		String retorno = goalCreated?"redirect:?action=Welcome":"redirect:?action=GoalForm";
		return retorno;
		 
	}

}
