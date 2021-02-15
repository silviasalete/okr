package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.User;

public class ResultKeyForm implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idGoal = request.getParameter("id");
		User 	 user = (User) request.getSession().getAttribute("user");
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("idGoal", idGoal);
		
		return "forward:resultKey.jsp";
	}

}
