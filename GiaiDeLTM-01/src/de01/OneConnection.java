package de01;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class OneConnection extends Thread {
	Socket serverSocket;
	DataInputStream netIn;
	DataOutputStream netOut;
	DAO dao = new DAO();
	
	public OneConnection(Socket socket) throws IOException {
		this.serverSocket = socket;
		netIn = new DataInputStream(serverSocket.getInputStream());
		netOut = new DataOutputStream(serverSocket.getOutputStream());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void close() throws IOException {
		netIn.close();
		netOut.close();
	}

	@Override
	public void run() {
		try {
			int number = netIn.readInt();
			switch (number) {
			case 1:
				getBalance();
				close();
				break;
			case 2:
				withdraw();
				close();
				break;
			case 3:
				deposit();
				break;
			case 4:
				transfer();
				break;
			case 5:
				report();
				break;
			default:
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // doc number de biet pt nao
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getBalance() throws IOException, SQLException {
		Account account = new Account();
		double amount = account.getBanlance(netIn.readUTF(), netIn.readUTF());
		netOut.writeDouble(amount);
	}
	
	private void withdraw() throws SQLException, IOException {
		Account account = new Account();
		boolean withdrawal = account.withdraw(netIn.readUTF(), netIn.readUTF(),netIn.readInt());
		netOut.writeBoolean(withdrawal);
	}
	
	private void deposit() throws SQLException, IOException {
		Account account = new Account();
		boolean check = account.deposit(netIn.readUTF(), netIn.readUTF(),netIn.readInt());
		netOut.writeBoolean(check);
	}
	private void transfer() throws IOException, SQLException {
		Account fromAccount = new Account(netIn.readUTF(), netIn.readUTF());
		Account toAccount = new Account(netIn.readUTF(), netIn.readUTF());
		boolean check = dao.transfer(fromAccount,toAccount,netIn.readInt());
		netOut.writeBoolean(check);
	}

	private void report() throws SQLException, IOException {
		String accountNumber = netIn.readUTF();
		List<Bill> list = dao.report(accountNumber, netIn.readUTF());
		netOut.writeUTF(accountNumber);//so tk
		for(Bill b : list){
			netOut.writeUTF(b.getDate());
			netOut.writeUTF(b.getAction());
			netOut.writeDouble(b.getAmount());
			netOut.writeDouble(b.getSum());
		}
	}

}
