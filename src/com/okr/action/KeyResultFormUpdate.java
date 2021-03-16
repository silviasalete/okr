package com.okr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okr.controller.Action;
import com.okr.model.bean.User;
import com.okr.model.dao.KeyResultDAO;

public class KeyResultFormUpdate implements Action {

	@Override
	public String performe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("userId",user.getId());
		KeyResultDAO keyResultDAO = new KeyResultDAO();
		
		request.setAttribute("keyResult",keyResultDAO.getKeyResultById(Integer.parseInt(request.getParameter("idKeyResult")))); 
		
		return "forward:keyResultUpdate.jsp";
	}

}
