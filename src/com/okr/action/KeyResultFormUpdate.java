package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.User;
import com.okr.model.dao.DataBase;

public class KeyResultFormUpdate implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("userId",user.getId());
		DataBase dataBase = new DataBase();
		
		request.setAttribute("keyResult",dataBase.getKeyResultById(Integer.parseInt(request.getParameter("id"))));
		
		return "forward:keyResultUpdate.jsp";
	}

}
