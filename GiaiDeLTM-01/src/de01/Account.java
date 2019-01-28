package de01;

import java.sql.SQLException;

public class Account {
	private String accountNumber;
	private String pinCode;
	private double amount;
	public Account(){}
	DAO dao = new DAO();
	
	public Account(String accountNumber, String pinCode,double amount) {
		super();
		this.accountNumber = accountNumber;
		this.pinCode = pinCode;
		this.amount = amount;
	}
	public Account(String accountNumber, String pinCode) {
		super();
		this.accountNumber = accountNumber;
		this.pinCode = pinCode;
	}
	public double getBanlance(String accountNumber, String pinCode) throws SQLException {
		return dao.getBanlance(accountNumber,pinCode);
	}
	public boolean withdraw(String accountNumber, String pinCode, int amount) throws SQLException {
		return dao.withdraw(accountNumber,pinCode,amount);
	}
	public boolean deposit(String accountNumber, String pinCode, int amount) throws SQLException {
		return dao.deposit(accountNumber,pinCode,amount);
	}
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", pinCode=" + pinCode + amount+ "]";
	}
	

	
	
}
