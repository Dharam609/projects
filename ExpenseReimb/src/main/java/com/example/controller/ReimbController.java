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

public class ReimbController {

	static ReimbService rs = new ReimbService();
	static UserService us = new UserService();
	
	public static String sumbitExpense(HttpServletRequest req) throws SQLException {
		if(!req.getMethod().equals("POST")) {
			return "reimbSumbit.html";
		} else {

			double amount = Double.parseDouble( req.getParameter("amount"));
			String description = req.getParameter("description");
			String type = req.getParameter("type");			
			rs.submitExpense(req, amount, description, type);
			return "employee.html";
			
//			if(!success) {
//				return "expenseReimbSumbit.html";
//			} 
//			else {
////				req.getSession().getAttribute("currentUser");			
				
				
//			}
			
		}
		
	}
	
	public static void setLReimbsAttribute(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, SQLException{
		List<Reimbursement> lReimbs = new ArrayList<>();
	
//		User user = (User) req.getSession().getAttribute("currentUser");
//		int roleId = user.getRoleId();
//		
//		if(roleId == 2) {
			lReimbs = rs.listAllReimbs();	
//		} else {
//			lReimbs = rs.listAllReimbsByUsername(user.getUsername());
//		}
//		Reimbursement test = new Reimbursement();
//		test.setAmount(roleId);
//		lReimbs.add(test);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(lReimbs));
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
