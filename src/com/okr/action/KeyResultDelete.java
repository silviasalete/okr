package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class KeyResultDelete implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataBase dataBase = new DataBase();
		User 		 user = (User) request.getSession().getAttribute("user");
		String    message = "Error when delete Key Result";
		
		int idUser 		= user.getId();
		int idObjective = Integer.parseInt(request.getParameter("idObjective"));
		int idKeuResult = Integer.parseInt(request.getParameter("idKeyResult"));
		
		if (dataBase.removeKeyResult(idUser, idObjective, idKeuResult)) {
			message = "Success when delete Key Result!";
		}
		request.setAttribute("message", message);
		return "redirect:?action=Welcome"; 
	}

}
