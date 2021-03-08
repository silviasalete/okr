package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.Objective;
import com.okr.model.dao.DataBase;

public class UpdateObjective implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  description = request.getParameter("description");
		String  objectiveId = request.getParameter("objectiveId");
		DataBase   dataBase = new DataBase();
		Objective objective = dataBase.getObjectiveById(Integer.parseInt(objectiveId));
		objective.setDescription(description);
		
		return dataBase.updateObjective(objective)?"redirect:?action=Welcome":"redirect:?action=ObjectiveFormUpdate";
	}

}
