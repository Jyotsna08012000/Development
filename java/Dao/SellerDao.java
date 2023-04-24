package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import Connection.DBconnection;
import Model.Seller;

public class SellerDao {
	public static Seller checkEmailduringRegister(String email) {
		Seller s = null;
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString="select * from seller where email=?";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setString(1, email);
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				s = new Seller();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setContact(rs.getLong("contact"));
			    s.setEmail(rs.getString("email"));
				s.setPassword(rs.getString("password"));
				s.setAddress(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	public static void insertSeller(Seller s) {
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString = "insert into seller(name,contact,email,password,address,city,state) values(?,?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setString(1, s.getName());
			pst.setLong(2, s.getContact());
			pst.setString(3, s.getEmail());
			pst.setString(4, s.getPassword());
			pst.setString(5, s.getAddress());
			pst.setString(6, s.getCity());
			pst.setString(7, s.getState());
			pst.executeUpdate();
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static Seller loginSeller(Seller s) {
		Seller s1=null;
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString = "select * from seller where email=? and password=?";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setString(1, s.getEmail());
			pst.setString(2, s.getPassword());
			ResultSet rSet= pst.executeQuery();
			if(rSet.next()) {
				s1=new Seller();
				s1.setId(rSet.getInt("id"));
				s1.setName(rSet.getString("name"));
				s1.setContact(rSet.getLong("contact"));
			    s1.setEmail(rSet.getString("email"));
				s1.setPassword(rSet.getString("password"));
				s1.setAddress(rSet.getString("address"));
				s1.setCity(rSet.getString("city"));
				s1.setState(rSet.getString("state"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s1;
		}
	public static Seller getSellerById(int id) {
		Seller s1 = null;
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString="select * from seller where id=?";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setInt(1, id);
			ResultSet rSet = pst.executeQuery();
			if(rSet.next()) {
				s1 = new Seller();
				s1.setId(rSet.getInt("id"));
				s1.setName(rSet.getString("name"));
				s1.setContact(rSet.getLong("contact"));
				s1.setEmail(rSet.getString("email"));
				s1.setAddress(rSet.getString("address"));
				s1.setCity(rSet.getString("city"));
				s1.setState(rSet.getString("state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s1;
	}
	public static void updateProfile(Seller s) {
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString="update seller set name=?,contact=?,email=?,address=?,city=?,state=? where id=?";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setString(1, s.getName());
			pst.setLong(2, s.getContact());
			pst.setString(3, s.getEmail());
			pst.setString(4, s.getAddress());
			pst.setString(5, s.getCity());
			pst.setString(6, s.getState());
		    pst.setInt(7, s.getId());
			pst.executeUpdate();
			System.out.println("data updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		public static boolean checkOldPassword(int id,String op) {
			boolean flag = false;
			try {
				Connection connection = DBconnection.getConnection();
				String sqlString="select * from seller where id=? and password=?";
				PreparedStatement pst = connection.prepareStatement(sqlString);
				pst.setInt(1, id);
				pst.setString(2, op);
				ResultSet rSet = pst.executeQuery();
				if(rSet.next()) {
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		public static void changePasswrod(int id,String np) {
			try {
				Connection connection = DBconnection.getConnection();
				String sqlString="update seller set password=? where id=?";
				PreparedStatement pst = connection.prepareStatement(sqlString);
				pst.setString(1, np);
				pst.setInt(2, id);
				pst.executeUpdate();
				System.out.println("password changed");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		public static boolean checkEmail(String email) {
			boolean flag=false;
			try {
				Connection connection = DBconnection.getConnection();
				String sqlString="select * from seller where email=?";
				PreparedStatement pst = connection.prepareStatement(sqlString);
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					flag=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		
		public static void changeNewPassword(String email, String np) {
			try {
				Connection connection = DBconnection.getConnection();
				String sqlString="update seller set password=? where email=?";
				PreparedStatement pst = connection.prepareStatement(sqlString);
				pst.setString(1, np);
				pst.setString(2, email);
				pst.executeUpdate();
				System.out.println("password changed");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public static List<Seller> getAllSellers(){
			List<Seller> list = new ArrayList<Seller>();
			try {
				Connection connection = DBconnection.getConnection();
				String sqlString="select * from seller";
				PreparedStatement pst = connection.prepareStatement(sqlString);
				ResultSet rSet = pst.executeQuery();
				while(rSet.next()) {
					Seller s1 = new Seller();
					s1.setId(rSet.getInt("id"));
					s1.setName(rSet.getString("name"));
					s1.setContact(rSet.getLong("contact"));
				    s1.setEmail(rSet.getString("email"));
					s1.setPassword(rSet.getString("password"));
					s1.setAddress(rSet.getString("address"));
					s1.setCity(rSet.getString("city"));
					s1.setState(rSet.getString("state"));
					list.add(s1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		public static void deleteSeller(int id){
			try {
				Connection connection = DBconnection.getConnection();
				String sqlString="delete from seller where id=?";
				PreparedStatement pst = connection.prepareStatement(sqlString);
				pst.setInt(1, id);
				pst.executeUpdate();
				System.out.println("data deleted");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
