package de201805;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import de201805.IFileManager;

public class Client {
	static IFileManager server;
	static String command, param;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Client client = new Client();
		client.connect();
		Scanner sc = new Scanner(System.in);
		String line;
		while (true) {
			line = sc.nextLine();
			if (line.equals("QUIT"))
				break;
			client.analysis(line);
			boolean check;
			switch (command) {
			case "SET_FOLDER":
				check = server.setFolder(param.trim());
				if (check)
					System.out.println("Set folder success!");
				else
					System.out.println("Error");
				break;
			case "VIEW":
				String v = server.view(param.trim());
				if(v != null)System.out.println(v);
				else System.out.println("not view");
				break;
			case "COPY":
				 client.analysis(param);
				check = server.copy(command,param);
				if(check) System.out.println("COPY success!");
				else if(!check)System.out.println("COPY fail");
				else System.out.println("ACCESS DENIED!");
				break;
			case "MOVE":
				 client.analysis(param);
				check = server.move(command,param);
				if(check) System.out.println("MOVE success!");
				else System.out.println("MOVE fail");
				break;
			case "RENAME":
				client.analysis(param);
				check = server.rename(command,param);
				if(check) System.out.println("RENAME success!");
				else System.out.println("RENAME fail");
				break;
			default: System.out.println("Command not found");
				break;
			}
		}
	}

	private void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line, "| ");
		command = "";
		command = token.nextToken().trim();
		param = "";
		while (token.hasMoreTokens()) {
			param += token.nextToken().trim() + " ";
		}
	}

	public void connect() throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 55555);
		server = (IFileManager) reg.lookup("file");
	}
}
