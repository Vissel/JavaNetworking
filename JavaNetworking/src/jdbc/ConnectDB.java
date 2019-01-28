package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:odbc:Library";
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connect = DriverManager.getConnection(url);
			if(connect != null) System.out.println("connect success");
			else System.out.println("Connect failed");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
