package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TestTransction {

	public static void main(String[] args) throws SQLException {
		
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=JAVA_NETWORKING";
		String user = "sa";
		String pass = "thach";
		Connection con = null;
		Savepoint savepoint = null;
		int timeStart = (int) System.currentTimeMillis();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);

			// Asume a valid connection object con
			con.setAutoCommit(false);
			Statement statement = con.createStatement();
			
			//set savepoint
			 savepoint = con.setSavepoint();
			 
			String sql1 = "INSERT INTO USERS(USERNAME,PASSWORD) VALUES('sweets','sweet')";
			statement.executeUpdate(sql1);

			// submit a malformed statement that breaks
			String sql2 = "INSERTED INTOs USERS(USERNAME,PASSWORD) VALUES('ice','ice')";
			statement.executeUpdate(sql2);

			// if there is no error
			con.commit();
			con.close();
		} catch (SQLException e) {
			//if there is any error
			e.printStackTrace();
			con.rollback(savepoint);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int endTime = (int) System.currentTimeMillis();
		System.out.println("time = " + (endTime - timeStart));
		
	}

}
