package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.Objective;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class ObjectiveFormUpdate implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User 	 	   user = (User) request.getSession().getAttribute("user");
		DataBase   database = new DataBase();
		Objective objective = database.getObjectiveById(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("objective", objective);
		
		return "forward:objectiveUpdate.jsp";
	}

}
