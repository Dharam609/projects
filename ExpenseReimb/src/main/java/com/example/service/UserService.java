package com.example.service;

import java.sql.SQLException;

import com.example.dao.UserDaoImpl;
import com.example.model.User;

public class UserService {
	
	private UserDaoImpl udi = new UserDaoImpl();

	public User getVerifiedUser(String username, String password) throws SQLException {
		User user = udi.getUserByUsername(username);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return user;
			}			
		} 
		return null;		
	}
	
	public User getUserById(int authId) throws SQLException {
		return udi.getUserById(authId);
	}
}
