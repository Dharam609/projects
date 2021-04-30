package com.example.dispatcher;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.example.controller.ReimbController;
import com.example.controller.UserController;

public class RequestDispatcher {
	
	public static String process(HttpServletRequest req) throws SQLException {
			
			switch(req.getRequestURI()) {
				case "/ExpenseReimb/login.dashboard":
					System.out.println("in login.dashboard dispatcher");
					return UserController.login(req);
				case "/ExpenseReimb/reimb.dashboard":
					System.out.println("in expenss.dashboard dispatcher");
					return ReimbController.sumbitExpense(req);					
				default:
						System.out.println("in default");
						return "/ExpenseReimb/login.change";			
			
			}
			
		}


}
