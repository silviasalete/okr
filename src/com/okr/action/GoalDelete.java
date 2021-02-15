package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class GoalDelete implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idGoal = Integer.parseInt(request.getParameter("id"));
		User  user = (User) request.getSession().getAttribute("user"); 		
		int idUser = user.getId();
		
		DataBase dataBase = new DataBase();

		if (dataBase.removeGoal(idGoal, idUser)) {
			
			request.setAttribute("message", "Goal Removed!");
		}else {
			
			request.setAttribute("message", "Error when remove goal!");
		}
		
		return "redirect:?action=Welcome";
	}

}
