package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.Goal;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class GoalFormUpdate implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User 	 	   user = (User) request.getSession().getAttribute("user");
		DataBase   database = new DataBase();
		Goal 		   goal = database.getGoalById(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("goal", goal);
		
		return "forward:goalUpdate.jsp";
	}

}
