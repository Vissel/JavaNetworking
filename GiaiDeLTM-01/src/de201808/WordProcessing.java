package de201808;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WordProcessing extends UnicastRemoteObject implements IWordProcessing {
	DAO dao = new DAO();
	String lastUser = null;
	String desDir = "D:\\eclipse-workspace\\GiaiDeLTM-01\\dest";
	File desFile;
	PrintWriter printW;
	BufferedReader reader;
	String totalCharacter = "";

	protected WordProcessing() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getWelcomeMessage() throws RemoteException {
		return "Welcome to Words Processing!";
	}

	@Override
	public String checkUser(String param) throws RemoteException {
		String checkU = dao.checkUser(param);
		this.lastUser = param;
		return checkU;
	}

	@Override
	public boolean login(String param) throws RemoteException {
		boolean checkLogin = false;
		if (lastUser != null) {
			checkLogin = dao.login(lastUser, param);
		}
		return checkLogin;
	}

	@Override
	public boolean addFile(String param) throws RemoteException {
		File file = new File(param);
		if (!file.exists())
			return false;
		else {
			try {
				desFile = new File(desDir + "\\" + file.getName());
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-16"));
				printW = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile), "UTF-16"));
				String line;
				while ((line = reader.readLine()) != null) {
					printW.println(line);
					totalCharacter += line + "\n";
				}
				reader.close();
				printW.close();
				return true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean addText(String param) throws RemoteException {
		try {
			printW = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile), "UTF-16"), true);
			totalCharacter += param + "\n";
			printW.println(totalCharacter);
			printW.close();
			return true;
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public double getSum() throws RemoteException {
		double result = 0;
		StringTokenizer token = new StringTokenizer(totalCharacter);

		int word = 0;
		while (token.hasMoreTokens()) {
			try {
				result += Double.parseDouble(token.nextToken());

			} catch (NumberFormatException e) {
				word++;
			}
		}
		return result;
	}

	@Override
	public int getWord() throws RemoteException {
		double result = 0;
		StringTokenizer token = new StringTokenizer(totalCharacter);

		double temp = 0;
		int word = 0;
		while (token.hasMoreTokens()) {
			try {
				temp += Double.parseDouble(token.nextToken());
				result++;
			} catch (NumberFormatException e) {
				word++;
			}
		}
		return word;
	}

	@Override
	public int getNums() throws RemoteException {
		int result = 0;
		StringTokenizer token = new StringTokenizer(totalCharacter);

		double temp = 0;
		int word = 0;
		while (token.hasMoreTokens()) {
			try {
				temp += Double.parseDouble(token.nextToken());
				result++;
			} catch (NumberFormatException e) {
				word++;
			}
		}
		return result;
	}

	@Override
	public List<String> getNumList() throws RemoteException {
		List<String> list = new ArrayList<>();
		StringTokenizer token = new StringTokenizer(totalCharacter);

		double number;
		String temp ="";
		while (token.hasMoreTokens()) {
			try {
				temp = token.nextToken();
//				number = Double.parseDouble(temp);
//				list.add(String.valueOf(number));
				list.add(temp);
			} catch (NumberFormatException e) {
//				temp = token.nextToken();
				list.add(temp);
			}
		}
		return list;
	}
}
