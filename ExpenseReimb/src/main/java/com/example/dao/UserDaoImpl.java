package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.utility.DaoUtility;

import com.example.model.User;

public class UserDaoImpl {
	
	private static Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public User insertUser(String username, String password, String fname, String lname, String email, int role_id) throws SQLException {
		User user = getUserByUsername(username);
		if(user == null) {
			try {
				con = DaoUtility.getConnection();
				String sql = "insert into users(username, password, first_name, last_name, email, role_id)"
						+ "values(?,?,?,?,?,?)";
				
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, fname);
				ps.setString(4, lname);
				ps.setString(5, email);
				ps.setInt(6, role_id);
				
				ps.execute();
				
				user = getUserByUsername(username);
				
				
			} catch (Exception e) {
				
			} finally {
//				con.close();
//				ps.close();
			}
		}
		return user;		
	}	
	
	public User getUserByUsername(String username) throws SQLException {
		User user = null;
		try {	
			con = DaoUtility.getConnection();
			
			String sql = "select * from users where username = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			con.close();
//			ps.close();
//			rs.close();
		}
		return user;
	}
	
	public User getUserById(int authorId) throws SQLException {
		User user = new User();
		
		try {	
			con = DaoUtility.getConnection();
			
			String sql = "select * from users where user_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, authorId);			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			con.close();
//			ps.close();
//			rs.close();
		}
		return user;
	}	
}
