package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestExtractColumn {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int timeStart = (int) System.currentTimeMillis();
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=JAVA_NETWORKING";
		String user = "sa";
		String pass = "thach";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, pass);
			
			DatabaseMetaData databaseMetaData = con.getMetaData();
			ResultSet rs = databaseMetaData.getColumns(null, null, "STUDENT", null);
			while(rs.next())
			{
				String columnName = rs.getString("COLUMN_NAME");
			    String datatype = rs.getString("DATA_TYPE");
			    String columnsize = rs.getString("COLUMN_SIZE");
			    String decimaldigits = rs.getString("DECIMAL_DIGITS");
			    String isNullable = rs.getString("IS_NULLABLE");
			    String is_autoIncrment = rs.getString("IS_AUTOINCREMENT");
			    //Printing results
			    System.out.println(columnName + "\t" + datatype + "\t" + columnsize + "\t" + decimaldigits + "\t" + isNullable + "\t" + is_autoIncrment );
			}
			int endTime = (int) System.currentTimeMillis();
			System.out.println("time = " + (endTime - timeStart));
			rs.close();
			con.close();
	}

}
