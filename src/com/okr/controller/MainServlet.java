package com.okr.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(this.getClass());
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/home.jsp");
		
		try { requestDispatcher.forward(request, response); } catch (Exception e) { System.out.println("ERROR1: "+e.getMessage()); }

	}
}
