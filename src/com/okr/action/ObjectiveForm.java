package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okr.controller.Action;
import com.okr.model.bean.User;

public class ObjectiveForm implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User 	 	   user = (User) request.getSession().getAttribute("user");
		request.setAttribute("userId", user.getId());
		
		return "forward:objective.jsp";
	}

}
