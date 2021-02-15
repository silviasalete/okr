package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okr.controller.Action;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class Login implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String    email = request.getParameter("email");
		String password = request.getParameter("password"); 
				
		DataBase dataBase = new DataBase();
		
		String redirect = "redirect:?action=LoginForm";
		User user = dataBase.userExists(email, password);
		
		if (user != null) {
			
					   redirect = "redirect:?action=Welcome";		
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		}
		
		return redirect;
	}

}
