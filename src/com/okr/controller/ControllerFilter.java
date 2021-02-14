package com.okr.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/")
public class ControllerFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		System.out.println(this.getClass());
		
		HttpServletRequest   request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String parameter = request.getParameter("action") == null ? "Home" :request.getParameter("action");		
		String      view;

			try {
				
				Class  parameterClass = Class.forName("com.okr.action."+parameter);
				Object    object = parameterClass.newInstance();
				Action    action = (Action) object;
				view = action.performe(request, response);
				
			} catch (Exception e) {
				
				System.out.println("ERROR4: "+e.getMessage());
				
				throw new ServletException();
				
			}
			
			String[] destinationType = view.split(":");

			if (destinationType[0].equals("redirect")) {

				response.sendRedirect(destinationType[1]);
				
			}else {
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/views/"+destinationType[1]);
				
				try { requestDispatcher.forward(request, response); } catch (Exception e) { System.out.println("ERROR1: "+e.getMessage()); }
				
			}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
