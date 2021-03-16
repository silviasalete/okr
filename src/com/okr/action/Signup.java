package com.okr.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.User;
import com.okr.model.dao.UserDAO;

public class Signup implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO userDAO = new UserDAO();
		String    retorno = "redirect:?action=SignupForm";
		int id = userDAO.addUser(new User(request.getParameter("name"),request.getParameter("email"),request.getParameter("password")));
 
		User user = userDAO.getUserById(id);
		if (user != null) {
			
			request.setAttribute("email", user.getEmail());	
			request.setAttribute("password", user.getPassword());	
			
			retorno = "forward:login.jsp";
		}else {
			System.out.println("ERROR3: Error when registering new user!");
		}

		return retorno; 
	}

}
