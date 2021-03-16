package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.Objective;
import com.okr.model.bean.User;
import com.okr.model.dao.ObjectiveDAO;
import com.okr.model.dao.UserDAO;

public class NewObjective implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  description      = request.getParameter("description");
		String       userId      = request.getParameter("userId");
		UserDAO 		 userDAO = new UserDAO();
		ObjectiveDAO objectiveDAO = new ObjectiveDAO();
		
		User 		   user      = userDAO.getUserById(Integer.parseInt(userId));
		Objective objective      = new Objective(description, user);
		boolean objectiveCreated = objectiveDAO.addObjective(objective);

		return objectiveCreated?"redirect:?action=Welcome":"redirect:?action=ObjectiveForm";
		 
	}

}
