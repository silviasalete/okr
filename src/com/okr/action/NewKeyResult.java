package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.KeyResult;
import com.okr.model.bean.User;
import com.okr.model.dao.KeyResultDAO;
import com.okr.model.dao.UserDAO;

public class NewKeyResult implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String 			   idUser = request.getParameter("userId");
		String 	      idObjective = request.getParameter("idObjective");
		String 		  description = request.getParameter("description");
		UserDAO 		  userDAO = new UserDAO();
		KeyResultDAO keyResultDAO = new KeyResultDAO();
		User 		 	     user = userDAO.getUserById(Integer.parseInt(idUser));		
		KeyResult 	    keyResult = new KeyResult(description, Integer.parseInt(idObjective), user); 
		
		return keyResultDAO.addListKeyResult(keyResult)?"redirect:?action=Welcome":"redirect:?action=KeyResultForm";
	}

}
