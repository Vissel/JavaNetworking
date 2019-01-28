package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMetaData {

	public static void main(String[] args) throws ClassNotFoundException {
		int timeStart = (int) System.currentTimeMillis();
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=JAVA_NETWORKING";
		String user = "sa";
		String pass = "thach";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, pass);
			
			DatabaseMetaData databaseMetaData = con.getMetaData();
			ResultSet rs = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
			System.out.println("Print table");
			while(rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
			int endTime = (int) System.currentTimeMillis();
			System.out.println("time = " + (endTime - timeStart));
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
