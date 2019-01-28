package review.finalexam.Exercise22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
//loi trim() cho param, test ham main bai 21 de hieu ro hon
public class OneConnection extends Thread {
	public Socket serverSocket;
	BufferedReader netIn;
	PrintWriter netOut;
	String command, param, lastUser = null;
	DAO dao = new DAO();

	public OneConnection(Socket socket) throws IOException {
		this.serverSocket = socket;
		netIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(), "UTF-8"));
		netOut = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()), true);
		netOut.println("welcome...");
	}

	@Override
	public void run() {
		String line;

		try {
			while (true) {
				line = netIn.readLine();
				if (line.equalsIgnoreCase("quit") || line == null) {
					netOut.println("end");
					break;
				}

				// analysis
				analysis(line);

				// login
				boolean checkLogin = login(command, param);
				while (checkLogin) {
					line = netIn.readLine();
					if (line.equalsIgnoreCase("exit") || line == null) {
						netOut.println("enter quit to exit");
						break;
					}
					// analysis
					analysis(line);
					// find
					List<Student> listSt;
					switch (command.toLowerCase()) {
					case "getbyid":
						String str = dao.findSTbyID(param.trim());
						if (str != null)
							netOut.println(str);
						else
							netOut.println(param + " is not found");
						break;
					case "getbyname":
						listSt = dao.findByName(param.trim());
						if (!listSt.isEmpty()) {
							for (Student st : listSt)
								netOut.println(st.toString());
						} else
							netOut.println(param + " not found");
						break;
					case "getbyage":
						listSt = dao.findByAge(param.trim());
						if (!listSt.isEmpty()) {
							for (Student st : listSt)
								netOut.println(st.toString());
						} else
							netOut.println(param + " not found");
						break;
					case "getbyscore":
						listSt = dao.findByScore(param.trim());
						if (!listSt.isEmpty()) {
							for (Student st : listSt)
								netOut.println(st.toString());
						} else
							netOut.println(param + " not found");
						break;
					default: netOut.println(param + " not found");
						break;
					}
				}
			}
			netIn.close();
			netOut.close();
			serverSocket.close();
			//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MyException nseEx) {
			netOut.println(nseEx.getMessage());
			run();
		}
	}

	private boolean login(String cmd, String par) {
		switch (command.toLowerCase()) {
		case "user":
			String temp = dao.checkUsername(par.trim());
			if (temp != null) {
				lastUser = temp;
				netOut.println(par + " is valid. Please enter password!");
			} else {
				netOut.println(par + " is invalid. Enter again !");
			}
			break;
		case "password":
			if (lastUser != null) {
				boolean authorization = dao.checkLogin(lastUser, par.trim());
				if (authorization) {
					netOut.println("Congratulation !login success!!");
					return true;
				} else {
					netOut.println(par + " is invalid. Enter again !");
				}
				break;
			} else {
				netOut.println("Please enter username !");
			}
			default : netOut.println("Command is not valid!");
			break;
		}
		return false;
	}

	private void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line);
		command = token.nextToken().trim();
		param = "";
		try {
			while (token.hasMoreTokens()) {
				param += token.nextToken() + " ";
			}
		} catch (NoSuchElementException ex) {
			throw new MyException("command is not valid!!!");
		}
	}

	public static void main(String[] args) {

	}
}
