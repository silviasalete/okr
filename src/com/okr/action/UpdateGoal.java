package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.Goal;
import com.okr.model.dao.DataBase;

public class UpdateGoal implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description");
		String goalId = request.getParameter("goalId");
		DataBase dataBase = new DataBase();
		Goal goal = dataBase.getGoalById(Integer.parseInt(goalId));
		goal.setDescription(description);
		
		return dataBase.updateGoal(goal)?"redirect:?action=Welcome":"redirect:?action=GoalFormUpdate";
	}

}
