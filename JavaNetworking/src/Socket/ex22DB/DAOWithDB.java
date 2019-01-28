package Socket.ex22DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Socket.ex22.Student;

public class DAOWithDB {
	private static Connection connect = null;
	
	public DAOWithDB() {
		connect = getConnection();
	}
	public Connection getConnection() {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=JAVA_NETWORKING";
		String user = "sa";
		String pass = "thach";
		try {
			Class.forName(driver);
			 connect = DriverManager.getConnection(url, user, pass);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException sql) {
			sql.printStackTrace();
		}
		return connect;
	}

	public boolean checkUser(String param) throws SQLException {
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		PreparedStatement prepared = connect.prepareStatement(sql);
		prepared.setString(1, param);
		ResultSet rs = prepared.executeQuery();
		if(rs.next()) return true;
		return false;
	}

	public boolean checkLogin(String lastUser, String param) throws SQLException {
		String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
		PreparedStatement prepared = connect.prepareStatement(sql);
		prepared.setString(1, lastUser);
		prepared.setString(2, param);
		ResultSet rs = prepared.executeQuery();
		if(rs.next()) return true;
		return false;
	}
	public String getID(String param) throws SQLException {
		String sql = "SELECT * FROM STUDENT WHERE ID = ?";
		PreparedStatement prepared = connect.prepareStatement(sql);
		prepared.setString(1, param);
		ResultSet rs = prepared.executeQuery();
		Student st = new Student() ;
		while(rs.next()) {
			st.setId(rs.getInt(1));
			st.setName(rs.getString(2));
			st.setAge(rs.getInt(3));
			st.setGrade(rs.getFloat(4));
		}
		rs.close();
		prepared.close();
		return st.toString();
	}
	public List<String> getNAME(String param) throws SQLException {
		String sql = "SELECT * FROM STUDENT WHERE NAME LIKE '%" + param + "%';";
		PreparedStatement prepared = connect.prepareStatement(sql);
//		prepared.setString(1, param);
		ResultSet rs = prepared.executeQuery();
		Student st;
		List<String> list = new ArrayList<>();
		while(rs.next()) {
			st = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
			list.add(st.toString());
		}
		rs.close();
		prepared.close();
		return list;
	}
	public List<String> getAGE(String param) throws SQLException {
		String sql = "SELECT * FROM STUDENT WHERE AGE = ?";
		PreparedStatement prepared = connect.prepareStatement(sql);
		prepared.setString(1, param);
		ResultSet rs = prepared.executeQuery();
		Student st ;
		List<String> list = new ArrayList<>();
		while(rs.next()) {
			st = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
			list.add(st.toString());
		}
		rs.close();
		prepared.close();
		return list;
	}
	public List<String> getGRADE(String param) throws SQLException {
		String sql = "SELECT * FROM STUDENT WHERE GRADE = ?";
		PreparedStatement prepared = connect.prepareStatement(sql);
		prepared.setString(1, param);
		ResultSet rs = prepared.executeQuery();
		Student st ;
		List<String> list = new ArrayList<>();
		while(rs.next()) {
			st = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
			list.add(st.toString());
		}
		rs.close();
		prepared.close();
		return list;
	}
	public static void main(String[] args) throws SQLException {
		List<String> list = new DAOWithDB().getNAME("NGUYEN");
		for(String s : list) System.out.println(s);
	}
}
