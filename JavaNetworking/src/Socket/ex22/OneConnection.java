package Socket.ex22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class OneConnection extends Thread {
	Socket serverSocket;
	PrintWriter netOut;
	BufferedReader netIn;
	DAO dao = new DAO();
	String lastUser = null;
	String line;

	public OneConnection(Socket socket) throws IOException {
		serverSocket = socket;
		netOut = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream(), "UTF-16"), true);
		netIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		netOut.println("welcome");
	}

	@Override
	public void run() {
		try {
			boolean success = login();
			while (success) {// login success
				// do something
				line = netIn.readLine();
				if (line.equalsIgnoreCase("EXIT"))
					break;

				StringTokenizer token = new StringTokenizer(line);
				String command = token.nextToken().toUpperCase();
				String param = token.nextToken();
				switch (command) {
				case "GETBYID":
					String findId = dao.getID(param);
					if (findId != null) {
						netOut.println(findId);
					} else
						netOut.println("id not found");
					break;
				case "GETBYNAME":
					List<String> findName = dao.getNAME(param);
					if (!findName.isEmpty()) {
						for (String st : findName)
							netOut.println(st);
					} else
						netOut.println("name not found");
					break;
				case "GETBYAGE":
					List<String> findAge = dao.getAGE(param);
					if (!findAge.isEmpty()) {
						for (String st : findAge)
							netOut.println(st);
					} else
						netOut.println("age not found");
					break;
				case "GETBYGRADE":
					List<String> findGRADE = dao.getGRADE(param);
					if (!findGRADE.isEmpty()) {
						for (String st : findGRADE)
							netOut.println(st);
					} else
						netOut.println("grade not found");
					break;
				default:
					netOut.println("command not found");
					break;
				}
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NoSuchElementException e) { // xử lí khi chỉ nhập 1 command ko hợp lệ
			netOut.println("command is invalid");
			run();
		}

	}

	private boolean login() throws IOException {

		boolean loop = false;
		while (!loop) {
			line = netIn.readLine();
			if (line.equalsIgnoreCase("EXIT")) {
				return false; // trả về giá trị false ->sẽ thoat ra khoi loop, và close socket
			}
			StringTokenizer token = new StringTokenizer(line);
			String command = token.nextToken().toUpperCase();
			String param = token.nextToken();
			boolean pass;
			switch (command) {
			case "USER":
				boolean user = dao.checkUser(param);
				if (user) {// xet user
					lastUser = param;
					netOut.println("user is valid");
				} else
					netOut.println("user is invalid");
				break;
			case "PASS":
				if (lastUser == null) { // nếu chưa nhập user thì hiển thị thông báo "no user"
					netOut.println("no user");
				} else {// nếu nhập user r thì đem xét với pass
					pass = dao.checkLogin(lastUser, param);
					if (pass) {
						netOut.println("login success");
						loop = true;
					} else
						netOut.println("pass is invalid");
				}
				break;
			default:
				netOut.println("no commands");
				break;
			}
		}
		return true;
	}
}
