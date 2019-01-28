package review.finalexam.Exercise21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

public class OneConnection extends Thread {
	private Socket serverSocket;
	BufferedReader netIn;
	PrintWriter netOut;
	String command,param;
	DAO dao = new DAO();

	public OneConnection(Socket socket) throws IOException {
		this.serverSocket = socket;
		netIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(),"UTF-8"));
		netOut = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()), true);
		netOut.println("welcome...");
	}

	@Override
	public void run() {
		try {
			String line;
			while (!(line = netIn.readLine()).isEmpty()) {
//				line = netIn.readLine();
				if (line.equalsIgnoreCase("exit")) {
					netOut.println("end");
					break;
				}
				// analysis
				analysis(line);
				List<String> list;
				switch (command) {
				case "GETBYNAME":
					list = dao.getByName(param.trim());
					if (!list.isEmpty()) {
						for (String st : list) {
							netOut.println(st);
						}
					} else {
						netOut.println("name not found");
					}
					break;
				case "GETBYAGE":
					list = dao.getByAge(param.trim());
					if (!list.isEmpty()) {
						for (String st : list) {
							netOut.println(st);
						}
					} else {
						netOut.println("age not found");
					}
					break;
				case "GETBYSCORE":
					list = dao.getByScore(param.trim());
					if (!list.isEmpty()) {
						for (String st : list) {
							netOut.println(st);
						}
					} else {
						netOut.println("score not found");
					}
					break;
				default:netOut.println("command is not valid");
					break;
				}
			}
			netIn.close();
			netOut.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line,"\t");
		command = token.nextToken().trim().toUpperCase();
		param = "";
		while (token.hasMoreTokens()) {
			param += token.nextToken() + "\t";
		}
	}

	public OneConnection() {
	}

	public static void main(String[] args) {
		OneConnection o = new OneConnection();
		o.analysis("getbyname nguyen");
		System.out.println(o.command + "\n" + o.param.trim());
		List<String> list = o.dao.getByName(o.param.trim());
		if(!list.isEmpty()) {
			for(String s : list) System.out.println(s);
		}else System.out.println("name not found");
		
	}

	
}
