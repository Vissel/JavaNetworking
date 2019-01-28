package de2014;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import de2014.IProductManager;

public class Client {
	static String command, param;
	static IProductManager server;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry(1212);
		server = (IProductManager) reg.lookup("product");
		if (server != null) {
			System.out.println(server.connect());
			Scanner sc = new Scanner(System.in);
			String line;
			while (true) {
				line = sc.nextLine();
				if (line.equals("EXIT"))
					break;
				analysis(line);
				boolean checkLogin = login(command, param);
				while (checkLogin) {
					// todo
					line = sc.nextLine();
					if (line.equals("QUIT"))
						break;
					analysis(line);
					boolean todo;
					switch (command) {
					case "ADD":
						todo = server.addP(param);
						if(todo) System.out.println("OK");
						else System.out.println("ERROR");
						break;
					case "REMOVE":
						int count = server.removeP(param);
						System.out.println("so luong remove: " +count);
						break;
					case "EDIT":
						todo = server.editP(param);
						if(todo) System.out.println("OK");
						else System.out.println("CAN NOT UPDATED");
						break;
					case "VIEW":
						List<String> list = server.view(param);
						if(!list.isEmpty()){
						for(String str : list)System.out.println(str);
						} else System.out.println("empty");
						break;
					default:System.out.println("command not founds");
						break;
					}
				}
			}
		} else
			System.out.println("connect error");
	}

	private static boolean login(String command2, String param2) throws RemoteException {
		String returnData;
		switch (command) {
		case "USER":
			returnData = server.checkUser(param2.trim());
			if (returnData.equals("OK"))
				System.out.println(returnData);
			break;
		case "PASS":
			returnData = server.login(param2.trim());
			if (returnData.equals("OK")) {
				System.out.println(returnData);
				return true;
			}
		default:
			System.out.println(param2 + "not exist");
			break;
		}
		return false;
	}

	private static void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line, "\t");
		command = token.nextToken().trim();
		param = "";
		while (token.hasMoreTokens()) {
			param += token.nextToken() + "\t";
		}

	}

}
