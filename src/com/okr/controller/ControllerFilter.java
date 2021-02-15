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
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws NullPointerException, IOException, ServletException {
				
		HttpServletRequest   request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String parameter = request.getParameter("action") == null ? "Home" :request.getParameter("action");		
		String      view = null;

			try {
				System.out.println("parameter: "+parameter);
				Class  parameterClass = Class.forName("com.okr.action."+parameter);
				Object    object = parameterClass.newInstance();
				Action    action = (Action) object;
				view = action.performe(request, response);
				
			} catch (ServletException e) {
				
				System.out.println("ERROR4 ServletException: "+e.getMessage());
				
//				throw new ServletException();
				
			} catch(Exception e){

				System.out.println("ERROR6 Exception: "+e.getMessage());
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
		
	}

	
}
