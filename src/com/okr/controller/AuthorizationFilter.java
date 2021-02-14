package com.okr.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		System.out.println(this.getClass());
		
		HttpServletRequest   request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;		
		String 		 parameterAction = request.getParameter("action");
		HttpSession 		 session =  request.getSession();
		boolean 	   userNotLogged = session.getAttribute("user") == null;
		
		if (parameterAction != null) { 
			
			boolean blockedAction = !(parameterAction.equals("LoginForm") || parameterAction.equals("Login") || parameterAction.equals("SignupForm") || parameterAction.equals("Signup"));
			
			if (blockedAction && userNotLogged) {
				response.sendRedirect("?action=LoginForm");
				return;
			}
		} 
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	 

}
