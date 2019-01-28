package de01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static Connection connect;

	public DAO() {
		connect = getConnection();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:odbc:LTMANG";
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public double getBanlance(String accountNumber, String pinCode) throws SQLException {
		String sql = "SELECT Amount FROM Account WHERE AccountNumber = ? AND PinCode = ?";
		PreparedStatement prepared = connect.prepareStatement(sql);
		prepared.setString(1, accountNumber);
		prepared.setString(2, pinCode);
		ResultSet rs = prepared.executeQuery();
		double amount = 0;
		while (rs.next()) {
			amount = rs.getDouble(1);
		}
		rs.close();
		prepared.close();
		return amount;
	}

	public boolean withdraw(String accountNumber, String pinCode, int amount) throws SQLException {
		String sql1 = "SELECT Amount FROM Account WHERE AccountNumber = ? AND PinCode = ?";
		PreparedStatement prepared = connect.prepareStatement(sql1);
		prepared.setString(1, accountNumber);
		prepared.setString(2, pinCode);
		ResultSet rs = prepared.executeQuery();
		double oldAmount = 0, currentAmount;
		while (rs.next()) {
			oldAmount = rs.getDouble(1);
		}
		rs.close();

		currentAmount = oldAmount - amount;
		String sql2 = "UPDATE Account SET Amount = ? WHERE AccountNumber = ? AND PinCode = ?";
		prepared = connect.prepareStatement(sql2);
		prepared.setDouble(1, currentAmount);
		prepared.setString(2, accountNumber);
		prepared.setString(3, pinCode);
		int checkUpdate = prepared.executeUpdate();
		addToBill(accountNumber," ","21/01/2019","Rut",amount,amount);
		prepared.close();
		
		if (checkUpdate == 1)
			return true;
		return false;
	}

	public boolean deposit(String accountNumber, String pinCode, int amount) throws SQLException {
		String sql1 = "SELECT Amount FROM Account WHERE AccountNumber = ? AND PinCode = ?";
		PreparedStatement prepared = connect.prepareStatement(sql1);
		prepared.setString(1, accountNumber);
		prepared.setString(2, pinCode);
		ResultSet rs = prepared.executeQuery();
		double oldAmount = 0, currentAmount;
		while (rs.next()) {
			oldAmount = rs.getDouble(1);
		}
		rs.close();

		currentAmount = oldAmount + amount;
		String sql2 = "UPDATE Account SET Amount = ? WHERE AccountNumber = ? AND PinCode = ?";
		prepared = connect.prepareStatement(sql2);
		prepared.setDouble(1, currentAmount);
		prepared.setString(2, accountNumber);
		prepared.setString(3, pinCode);
		int checkUpdate = prepared.executeUpdate();
		addToBill(accountNumber," ","22/01/2019","Goi",amount,amount);
		prepared.close();
		
		if (checkUpdate == 1)
			return true;
		return false;
	}
	private void addToBill(String fromAccount, String toAccount, String date, String action, double amount,
			double sum) throws SQLException {
		String sql = "INSERT INTO Bill(FromAcc,ToAcc,Date,Actions,Amount,Sum) "
				+ "VALUES(?,?,?,?,?,?)";
		PreparedStatement prepared = connect.prepareStatement(sql);
		prepared.setString(1, fromAccount);
		prepared.setString(2, toAccount);
		prepared.setString(3, date);
		prepared.setString(4, action);
		prepared.setDouble(5, amount);
		prepared.setDouble(6, sum);
		prepared.executeUpdate();
		prepared.close();
	}

	public boolean transfer(Account fromAccount, Account toAccount, int amount) throws SQLException {
		boolean check1 = withdraw(fromAccount.getAccountNumber(), fromAccount.getPinCode(), amount);
		boolean check2 = deposit(toAccount.getAccountNumber(), toAccount.getPinCode(), amount);
		addToBill(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), "22/01/2019", "Chuyen Khoan", amount, amount);
		if(check1 && check2) return true;
		return false;
	}

	public List<Bill> report(String accountNumber, String pinCode) throws SQLException {
		String sql = "SELECT *"
				+ " FROM Bill "
				+ "WHERE FromAcc = ? OR ToAcc = ?";
		PreparedStatement prepared = connect.prepareStatement(sql);
		prepared.setString(1, accountNumber);
		prepared.setString(2, accountNumber);
		ResultSet rs = prepared.executeQuery();
		List<Bill> list = new ArrayList<>();
		Bill bill;
		while (rs.next()) {
			bill = new Bill();
			bill.setFromAccount(rs.getString(2));
			bill.setToAccount(rs.getString(3));
			bill.setDate(rs.getString(4));
			bill.setAction(rs.getString(5));
			bill.setAmount(rs.getDouble(6));
			bill.setSum(rs.getDouble(7));
			list.add(bill);
		}
		rs.close();
		prepared.close();
		return list;
	}

}
