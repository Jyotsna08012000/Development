package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.DBconnection;

public class AdminDao {
	public static void adminLogin(String email,String password) {
	
	try {
		Connection conn = DBconnection.getConnection();
		String sql = "select * from admin where email=? and password=?";
		PreparedStatement pst =conn.prepareStatement(sql);
		pst.setString(1, email);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	}
}
