package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class ObjectiveDelete implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int   idObjective = Integer.parseInt(request.getParameter("id"));
		User  	   	 user = (User) request.getSession().getAttribute("user"); 		
		int		   idUser = user.getId();		
		DataBase dataBase = new DataBase();

		if (dataBase.removeObjective(idObjective, idUser)) {
			
			request.setAttribute("message", "Objective Removed!");
		}else {
			
			request.setAttribute("message", "Error when remove objective!");
		}
		
		return "redirect:?action=Welcome";
	}

}
