package de201808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import de201808.IWordProcessing;

public class Client {
	static IWordProcessing server;
	static String command, param;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 22222);
		server = (IWordProcessing) reg.lookup("word");
		if (server != null) {
			String welcome = server.getWelcomeMessage();
			System.out.println(welcome);
			try {
				Scanner in = new Scanner(System.in);
				String line;
				while (true) {
					line = in.nextLine();
					if (line.equalsIgnoreCase("QUIT"))
						break;
					analysis(line);

					boolean login = login();
					while (login) {
						// todo
						line = in.nextLine();
						if (line.equalsIgnoreCase("QUIT"))
							break;
						analysis(line);
						boolean check;
						switch (command.toUpperCase()) {
						case "ADD_FILE":
							check = server.addFile(param.trim());
							if (check)
								System.out.println("ADD FILE success");
							else
								System.out.println("ADD FILE failed");
							break;
						case "ADD_TEXT":
							check = server.addText(param.trim());
							if (check)
								System.out.println("ADD TEXT success");
							else
								System.out.println("ADD TEXT failed");
							break;
						case "GET_SUM":
							double sum = server.getSum();
							System.out.println(sum);
							break;
						case "GET_NUMS":
							double num = server.getNums();
							System.out.println(num);
							break;
						case "GET_WORDS":
							double word = server.getWord();
							System.out.println(word);
							break;
						case "GET_NUM_LIST":
							List<String> list = server.getNumList();
							for (String s : list)
								System.out.println(s);
							break;
						default:
							System.out.println("Command not found");
							break;
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			System.out.println("Connect failed");
	}

	private static boolean login() throws RemoteException {
		boolean login = false;
		switch (command.toUpperCase()) {
		case "USERNAME":
			String checkU = server.checkUser(param.trim());
			if (checkU != null)
				System.out.println("username is correct");
			else
				System.out.println("username not correct");
			break;
		case "PASSWORD":
			login = server.login(param.trim());
			if (login) {
				System.out.println("login success");
				return login;
			} else
				System.out.println("password is incorrect");
			break;
		default:
			System.out.println("Command not found");
			break;
		}
		return login;
	}

	private static void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line);
		command = token.nextToken().trim();
		param = "";
		while (token.hasMoreTokens())
			param += token.nextToken() + " ";
	}

}
