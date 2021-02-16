package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.KeyResult;
import com.okr.model.dao.DataBase;

public class UpdateKeyResult implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String description = request.getParameter("description");
		String keyResultId = request.getParameter("keyResultId");
		
		DataBase dataBase = new DataBase();
		
		KeyResult keyResult = dataBase.getKeyResultById(Integer.parseInt(keyResultId));
		keyResult.setDescription(description);
		
		return dataBase.updateKeyResult(keyResult)?"redirect:?action=Welcome":"redirect:?action=KeyResultFormUpdate";
	}

}
