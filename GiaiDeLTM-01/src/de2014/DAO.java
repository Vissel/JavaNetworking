package de2014;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	static Connection connection;

	public static void main(String[] args) throws SQLException {
		DAO d = new DAO();
		System.out.println(d.login("thach", "thach"));
	}

	public DAO() {
		try {
			connection = getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:odbc:SanPham";
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}

	public String checkUser(String param) throws SQLException {
		String sql = "SELECT * FROM User WHERE username = ?";
		PreparedStatement prepared = connection.prepareStatement(sql);
		prepared.setString(1, param);
		ResultSet rs = prepared.executeQuery();
		String username = null;
		while (rs.next()) {
			username = rs.getString(1);
		}
		rs.close();
		prepared.close();
		return username;
	}

	public boolean login(String lastUser, String param) throws SQLException {
		String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
		PreparedStatement prepared = connection.prepareStatement(sql);
		prepared.setString(1, lastUser);
		prepared.setString(2, param);
		ResultSet rs = prepared.executeQuery();

		while (rs.next()) {
			return true;
		}
		rs.close();
		prepared.close();
		return false;
	}

	public boolean addP(Product product) throws SQLException {
		String sql = "INSERT INTO SanPham(name,count,price) VALUES(?,?,?)";
		PreparedStatement prepared = connection.prepareStatement(sql);
		prepared.setString(1, product.name);
		prepared.setInt(2, product.count);
		prepared.setDouble(3, product.price);
		prepared.executeUpdate();
		return true;
	}

	public int removeP(int parseInt) throws SQLException {
		String sql = "DELETE FROM SanPham WHERE idsp = ?";
		PreparedStatement prepared = connection.prepareStatement(sql);
		prepared.setInt(1, parseInt);
		int number = prepared.executeUpdate();
		return number;
	}

	public boolean editP(Product product) throws SQLException {
		String sql = "UPDATE SanPham SET name=?,count=?,price=? WHERE idsp = ?";
		PreparedStatement prepared = connection.prepareStatement(sql);
		prepared.setString(1, product.name);
		prepared.setInt(2, product.count);
		prepared.setDouble(3, product.price);
		prepared.setInt(4, product.id);
		int number = prepared.executeUpdate();
		if (number == 1)
			return true;
		else
			return false;
	}

	public String viewP(String str) throws SQLException {
		String sql = "SELECT * FROM SanPham WHERE name LIKE '%"+str+"%'";
		PreparedStatement prepared = connection.prepareStatement(sql);
		ResultSet rs  = prepared.executeQuery();
		Product pro = new Product() ;
		while(rs.next()){
			pro = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
		}
		return pro.toString();
	}

}
