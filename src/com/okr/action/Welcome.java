package com.okr.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okr.controller.Action;
import com.okr.model.bean.Goal;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class Welcome implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataBase   dataBase = new DataBase();
		HttpSession session = request.getSession();
		User 		   user = (User) session.getAttribute("user");
		List<Goal> listGoal = dataBase.getListGoalByUser(user);
		
		request.setAttribute("listGoal", listGoal);
		
		return "forward:welcome.jsp";
	}

}
