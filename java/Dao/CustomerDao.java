package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.DBconnection;
import Model.Customer;
import Model.Seller;

public class CustomerDao {
	public static Customer checkEmailduringRegister(String email) {
		Customer c = null;
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString="select * from customer where email=?";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setString(1, email);
			ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				c = new Customer();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setContact(rs.getLong("contact"));
			    c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setAddress(rs.getString("address"));
				c.setCity(rs.getString("city"));
				c.setState(rs.getString("state"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	public static void insertCustomer(Customer c) {
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString = "insert into customer(name,contact,email,password,address,city,state) values(?,?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setString(1, c.getName());
			pst.setLong(2, c.getContact());
			pst.setString(3, c.getEmail());
			pst.setString(4, c.getPassword());
			pst.setString(5, c.getAddress());
			pst.setString(6, c.getCity());
			pst.setString(7, c.getState());
			pst.executeUpdate();
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static Customer loginCustomer(Customer c) {
		Customer c1=null;
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString = "select * from customer where email=? and password=?";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setString(1, c.getEmail());
			pst.setString(2, c.getPassword());
			ResultSet rSet= pst.executeQuery();
			if(rSet.next()) {
				c1=new Customer();
				c1.setId(rSet.getInt("id"));
				c1.setName(rSet.getString("name"));
				c1.setContact(rSet.getLong("contact"));
			    c1.setEmail(rSet.getString("email"));
				c1.setPassword(rSet.getString("password"));
				c1.setAddress(rSet.getString("address"));
				c1.setCity(rSet.getString("city"));
				c1.setState(rSet.getString("state"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
		}
	public static Customer getSellerById(int id) {
		Customer c1 = null;
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString="select * from customer where id=?";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setInt(1, id);
			ResultSet rSet = pst.executeQuery();
			if(rSet.next()) {
				c1 = new Customer();
				c1.setId(rSet.getInt("id"));
				c1.setName(rSet.getString("name"));
				c1.setContact(rSet.getLong("contact"));
				c1.setEmail(rSet.getString("email"));
				c1.setAddress(rSet.getString("address"));
				c1.setCity(rSet.getString("city"));
				c1.setState(rSet.getString("state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
	}
	public static void updateProfile(Customer c) {
		try {
			Connection connection = DBconnection.getConnection();
			String sqlString="update cutomer set name=?,contact=?,email=?,address=?,city=?,state=? where id=?";
			PreparedStatement pst = connection.prepareStatement(sqlString);
			pst.setString(1, c.getName());
			pst.setLong(2, c.getContact());
			pst.setString(3, c.getEmail());
			pst.setString(4, c.getAddress());
			pst.setString(5, c.getCity());
			pst.setString(6, c.getState());
		    pst.setInt(7, c.getId());
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
				String sqlString="select * from customer where id=? and password=?";
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
				String sqlString="update customer set password=? where id=?";
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
				String sqlString="select * from customer where email=?";
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
				String sqlString="update customer set password=? where email=?";
				PreparedStatement pst = connection.prepareStatement(sqlString);
				pst.setString(1, np);
				pst.setString(2, email);
				pst.executeUpdate();
				System.out.println("password changed");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public static List<Customer> getAllCustomers(){
			List<Customer> list = new ArrayList<Customer>();
			try {
				Connection conn = DBconnection.getConnection();
				String sql="select * from customer";
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					Customer c1 = new Customer();
					c1.setId(rs.getInt("id"));
					c1.setName(rs.getString("name"));
					c1.setContact(rs.getLong("contact"));
				    c1.setEmail(rs.getString("email"));
					c1.setPassword(rs.getString("password"));
					c1.setAddress(rs.getString("address"));
					c1.setCity(rs.getString("city"));
					c1.setState(rs.getString("state"));
					list.add(c1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		public static void deleteCustomer(int id){
			try {
				Connection connection = DBconnection.getConnection();
				String sqlString="delete from customer where id=?";
				PreparedStatement pst = connection.prepareStatement(sqlString);
				pst.setInt(1, id);
				pst.executeUpdate();
				System.out.println("data deleted");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}



