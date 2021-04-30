package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.service.ReimbService;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {
	static UserService us = new UserService();
	static ReimbService rs = new ReimbService();
	
	
	public static String login(HttpServletRequest req) throws SQLException {
		if(!req.getMethod().equals("POST")) {
			return "login.html";
		}
		String username = req.getParameter("username");
		User user = us.getVerifiedUser(req.getParameter("username"), req.getParameter("password"));
		
		if(user == null) {
			return "login.html";
		} 
		else {
			req.getSession().setAttribute("currentUser", user);
			HttpSession session=req.getSession(true);
			session.setAttribute("username", username);
			if(user.getRoleId() == 1) {
				return "employee.html";
			} else {
				return "manager.html";
			}
		}
	}
	
	public static void getUserSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		User user = (User) req.getSession().getAttribute("currentUser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(user));
	}
	
	public static void setUserReimbList(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, SQLException{
		List<Reimbursement> lReimbs = new ArrayList<>();
		HttpSession session=req.getSession(true);
		String username = (String) session.getAttribute("username");
		System.out.println(username);
//		User user = new User();
//		user = (User) session.getAttribute("loginUser");
		lReimbs = rs.listAllReimbsByUsername(username);		
		res.getWriter().write(new ObjectMapper().writeValueAsString(lReimbs));
	}

}
