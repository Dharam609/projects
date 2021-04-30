package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.utility.DaoUtility;

public class ReimbDaoImpl {
	
	UserDaoImpl udi = new UserDaoImpl();
	DaoUtility dUtil;
	private static Connection con = null;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ReimbDaoImpl() {
		dUtil = new DaoUtility();
	}
	
	public boolean insertReimb(double amount, String description, int authorId, int typeId) throws SQLException {
				
		try {	
				con = DaoUtility.getConnection();

				String sql = "insert into reimbursements (amount, description, author_id, status_id, type_id) values(?,?,?,?,?)";
							
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDouble(1, amount);
				ps.setString(2, description);					
				ps.setInt(3, authorId);					
				ps.setInt(4, 2);
				ps.setInt(5, typeId);
				
				int success = ps.executeUpdate();
				if(success == 1) {
					return true;
				}				
				
				System.out.println("Inserted Successfully");			
		
		} catch (Exception e) {
			
		} finally {
//			con.close();
//			ps.close();
		}
		return false;
	}
	
//	public void deleteReimb(Reimbursement reimb, String username) {
//		User user = udi.getUserByUsername(username);
//					Reimbursement compareReimb = new Reimburesement(r.getAmount(), r.getSubmittedDate(), r.getResolvedDate(), r.getDescription(), r.getAuthorId(),)
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
	
	public List<Reimbursement> getReimbsByUsername(String username) throws SQLException {		
		List<Reimbursement> lReimb = new ArrayList<>();
		User user = udi.getUserByUsername(username);
		if(user != null) {
			try {	
				con = DaoUtility.getConnection();
				String sql = "select * from reimbursements where author_id = ?";
				
				ps = con.prepareStatement(sql);
				ps.setInt(1, user.getUserId());
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					String applicant = udi.getUserById(rs.getInt(7)).getFname();
					String managerName = udi.getUserById(rs.getInt(8)).getFname();
					String status = "";
					switch(rs.getInt(9)) {
					case 1:
						status = "Approved";
						break;
					case 2:
						status = "Pending";
						break;
					case 3:
						status = "Denied";
						break;
					default:
						break;
					}
					
					String type = "";
					switch(rs.getInt(10)) {
					case 1:
						type = "Lodging";
						break;
					case 2:
						type = "Travel";
						break;
					case 3:
						type = "Food";
						break;
					case 4:
						type = "Other";
						break;
					default:
						break;
					}
					lReimb.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), applicant, managerName, status, type));
				}
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally {
//				con.close();
//				ps.close();
//				rs.close();
			}
			
		}
		
		return lReimb;
	}
	
	public List<Reimbursement> getAllReimbs() throws SQLException {		
		List<Reimbursement> lReimb = new ArrayList<>();		
	
		try {	
				con = DaoUtility.getConnection();
				String sql = "select * from reimbursements";
				
				ps = con.prepareStatement(sql);					
				rs = ps.executeQuery();
				while(rs.next()) {
					String applicant = udi.getUserById(rs.getInt(7)).getFname();
					String managerName = udi.getUserById(rs.getInt(8)).getFname();
					String status = "";
					switch(rs.getInt(9)) {
					case 1:
						status = "Approved";
						break;
					case 2:
						status = "Pending";
						break;
					case 3:
						status = "Denied";
						break;
					default:
						break;
					}
					
					String type = "";
					switch(rs.getInt(10)) {
					case 1:
						type = "Lodging";
						break;
					case 2:
						type = "Travel";
						break;
					case 3:
						type = "Food";
						break;
					case 4:
						type = "Other";
						break;
					default:
						break;
					}
					lReimb.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), applicant, managerName, status, type));
				}		
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
//				con.close();
//				ps.close();
//				rs.close();
			}
		
		return lReimb;
	}
	public boolean update(int id) {
		
		return false;
	}
	
	
//	public boolean checkAuthId(int id) {
//		
//		try(Connection con = DaoUtility.getConnection()) {
//			String sql = "select user_id from users where user_id = ?";
//			
//			try(PreparedStatement ps = con.prepareStatement(sql)){
//				ps.setInt(1, id);			
//			
//			try(ResultSet rs = ps.executeQuery()){
//				while(rs.next()) {
//					if(id == rs.getInt(1)) {
//						return true;
//					}
//				}	
//			}
//		}
//			
//			
//		} catch (Exception e) {
//			
//		}
//		return false;
//	}	
//	
//	
//	public boolean checkTypeId(int id) {
//		try(Connection con = DaoUtility.getConnection()) {
//			String sql = "select type_id from reimbursement_type where type_id = ?";
//			
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setInt(1, id);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				if(id == rs.getInt(1)) {
//					return true;
//				}	
//				
//			}
//			
//			
//		} catch (Exception e) {
//			
//		}
//		return false;
//	}
//	
//	public static void main(String[] args) throws SQLException {
//		ReimbDaoImpl rdi = new ReimbDaoImpl();
//		List<Reimbursement> lr = rdi.getReimbsByUsername("Dharam");
//		for(Reimbursement rm : lr) {
//			System.out.println(rm.getType());			
//		}
//	}

}
