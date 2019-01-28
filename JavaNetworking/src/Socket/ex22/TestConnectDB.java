package Socket.ex22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestConnectDB {

	public static void main(String[] args) {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=JAVA_NETWORKING";
		String user = "sa";
		String pass = "thach";
		
		List<User> list = new ArrayList<User>();
		try {
			Class.forName(driver);
			Connection connect = DriverManager.getConnection(url, user, pass);
			
			String sql = "UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?";
			PreparedStatement prepared = connect.prepareStatement(sql);
			prepared.setString(1, "deptrai");
			prepared.setString(2, "han");
			prepared.executeUpdate();
			
			prepared.close();
			connect.close();
			
			for(User r : list )System.out.println(r.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException sql) {
			sql.printStackTrace();
		}
	}

}
