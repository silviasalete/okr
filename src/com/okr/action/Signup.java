package com.okr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class Signup implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataBase dataBase = new DataBase();
		String    retorno = "redirect:?action=SignupForm";
		
		if (dataBase.addListUser(new User(request.getParameter("name"),request.getParameter("email"),request.getParameter("password")))) {

			List<User> listUser = dataBase.getListUser();			
			User 		  userA = new User();
			
			for (User user : listUser) {
				
				if (user.getEmail() == request.getParameter("email")) {
					
					userA = user;
				}
			}

			request.setAttribute("email", userA.getEmail());	
			request.setAttribute("password", userA.getPassword());	
			
			retorno = "forward:login.jsp";
			
		}else {
			System.out.println("ERROR3: Error when registering new user!");
		}

		return retorno; 
	}

}
