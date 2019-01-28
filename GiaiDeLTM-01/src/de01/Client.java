package de01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;

public class Client {
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
		client.connection();
		Account account = new Account("AA12345", "12345678");
//		 double number1 = client.getBanlance(account);
//		 System.out.println(number1);
		 boolean number2 = client.withdraw(account, 50000);
		 System.out.println(number2);
//		boolean number3 = client.deposit(account, 100000);
//		System.out.println(number3);
//		boolean number4 = client.transfer(account, new Account("BB09876", "thach22"), 200000);
//		System.out.println(number4);
//		client.report(account);
	}

	public void connection() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 12345);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
	}

	public void close() throws IOException {
		dis.close();
		dos.close();
	}

	public double getBanlance(Account account) {
		double amount = 0;
		try {
			dos.writeInt(1);// send number 1
			dos.writeUTF(account.getAccountNumber());
			dos.writeUTF(account.getPinCode());
			dos.flush();
			amount = dis.readDouble();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}

	public boolean withdraw(Account account, int amount) {
		boolean withdrawal = false;
		try {
			dos.writeInt(2);// send number 2
			dos.writeUTF(account.getAccountNumber());
			dos.writeUTF(account.getPinCode());
			dos.writeInt(amount); // so tien rut
			dos.flush();
			withdrawal = dis.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return withdrawal;
	}

	public boolean deposit(Account account, int amount) {
		boolean check = false;
		try {
			dos.writeInt(3);// send number 3
			dos.writeUTF(account.getAccountNumber());
			dos.writeUTF(account.getPinCode());
			dos.writeInt(amount); // so tien goi
			dos.flush();
			check = dis.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public boolean transfer(Account fromAccount, Account toAccount, int amount) {
		boolean check = false;
		try {
			dos.writeInt(4);// send number 4
			dos.writeUTF(fromAccount.getAccountNumber());
			dos.writeUTF(fromAccount.getPinCode());
			dos.writeUTF(toAccount.getAccountNumber());
			dos.writeUTF(toAccount.getPinCode());
			dos.writeInt(amount); // so tien chuyen khoan
			dos.flush();
			check = dis.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public void report(Account account) {
		try {
			dos.writeInt(5);// send number 3
			dos.writeUTF(account.getAccountNumber());
			dos.writeUTF(account.getPinCode());
			dos.flush();
			String accountNumber = dis.readUTF();//so tai khoan
			System.out.println("Tên tài khoản số : " + accountNumber);
			System.out.println("Ngay thang\t Thao tác\t Số lượng\t TỔng số");
			int sizeList = dis.readInt();//size lsit
			Bill bill;
			for (int i = 0; i < sizeList; i++) {
				bill = new Bill();
				bill.setDate(dis.readUTF());
				bill.setAction(dis.readUTF());
				bill.setAmount(dis.readDouble());
				bill.setSum(dis.readDouble());
				System.out.println(bill.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
