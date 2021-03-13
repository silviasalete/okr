package com.okr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okr.controller.Action;
import com.okr.model.bean.Objective;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;
import com.okr.utils.Utils;

public class Welcome implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			DataBase   			 dataBase = new DataBase();
			Utils 				    utils = new Utils();
			HttpSession 		  session = request.getSession();
			User 		   			 user = (User) session.getAttribute("user"); 
			user.setName(utils.returnNameRequired(user.getName(), 1));			
			
			List<Objective> listObjective = dataBase.getListObjectiveByUser(user); 
			
			for (Objective objective : listObjective) {
				objective.setListKeyResult(dataBase.getListKeyResultByObjectiveId(objective.getId(), objective.getUser().getId()));
			}
			
		request.setAttribute("listObjective", listObjective);
		
		return "forward:welcome.jsp";
	}

}
