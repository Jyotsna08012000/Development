package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.DBconnection;
import Model.Cart;


public class CartDao {
	public static void insertCart(Cart c) {
		try {
			Connection conn = DBconnection.getConnection();
			String sql = "insert into cart(cusid,pid,image,pname,pprice,pqty,payment_status,total) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getCusid());
			pst.setInt(2, c.getPid());
			pst.setString(3,c.getImage());
			pst.setString(4, c.getPname());
			pst.setInt(5, c.getPprice());
			pst.setInt(6, c.getPqty());
			pst.setString(7, c.getPayment_status());
			pst.setInt(8, c.getTotal());
			pst.executeUpdate();
			System.out.println("added");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Cart> getCartByCusId(int id) {
		List<Cart> list = new ArrayList<Cart>();
		try {
			Connection conn = DBconnection.getConnection();
			String sql = "select * from cart where cusid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cart c = new Cart();
				c.setCid(rs.getInt("cid"));
				c.setCusid(rs.getInt("cusid"));
				c.setPid(rs.getInt("pid"));
				c.setImage(rs.getString("image"));
				c.setPname(rs.getString("pname"));
				c.setPprice(rs.getInt("pprice"));
				c.setPqty(rs.getInt("pqty"));
				c.setPayment_status(rs.getString("payment_status"));
				c.setTotal(rs.getInt("total"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Cart getCartByCid(int cid) {
		Cart c = null;
		try {
			Connection conn = DBconnection.getConnection();
			String sql = "select * from cart where cid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				c = new Cart();
				c.setCid(rs.getInt("cid"));
				c.setCusid(rs.getInt("cusid"));
				c.setPid(rs.getInt("pid"));
				c.setImage(rs.getString("image"));
				c.setPname(rs.getString("pname"));
				c.setPprice(rs.getInt("pprice"));
				c.setPqty(rs.getInt("pqty"));
				c.setPayment_status(rs.getString("payment_status"));
				c.setTotal(rs.getInt("total"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	public static void updateCart(Cart c) {
		try {
			Connection conn = DBconnection.getConnection();
			String sql = "update cart set pqty=?,total=? where cid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getPqty());
			pst.setInt(2, c.getTotal());
			pst.setInt(3, c.getCid());
			pst.executeUpdate();
			System.out.println("cart updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void removeFromCart(int cid) {
		try {
			Connection conn = DBconnection.getConnection();
			String sql= "delete from cart where cid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean cheProductExist(int cusid,int pid) {
		boolean flag = false;
		try {
			Connection conn = DBconnection.getConnection();
			String sql= "select * from cart where cusid=? and pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, cusid);
			pst.setInt(2, pid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}

