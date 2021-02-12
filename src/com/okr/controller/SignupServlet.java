package com.okr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/signup.jsp"); 
		
		try { requestDispatcher.forward(request, response); } catch (Exception e) { System.out.println("ERROR2: "+e.getMessage()); }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		DataBase dataBase     = new DataBase();

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/signup.jsp");
		
		if (dataBase.addListUser(new User(request.getParameter("name"),request.getParameter("email"),request.getParameter("password")))) {

			List<User> listUser = dataBase.getListUser();
			
			for (User user : listUser) {
				System.out.println(user.toString());
			}
			
			request.getRequestDispatcher("views/welcome.jsp");
			
		}else {
			System.out.println("nok");
		}
		
		try { requestDispatcher.forward(request, response); } catch (Exception e) { System.out.println("ERROR3: "+e.getMessage());}
	}
}
