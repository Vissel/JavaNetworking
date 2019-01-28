package review.finalexam.rmi4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StudentListIml extends UnicastRemoteObject implements IStudentList {
	String command, param, lastUser = null;
	DAO dao = new DAO();

	protected StudentListIml() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws RemoteException {
		StudentListIml s = new StudentListIml();
//		s.analysis("getbyid 123");
//		System.out.println(s.command + "\n" + s.param);
//		System.out.println(s.login("user thach"));
//		System.out.println(s.login("password thach"));
//		List<String> list = s.execute("getbyscore 10");
//		System.out.println(list);
	}

	public void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line);
		command = token.nextToken().trim();
		param = "";
		while (token.hasMoreTokens()) {
			param += token.nextToken() + " ";
		}
	}

	@Override
	public String login(String line) throws RemoteException {
		analysis(line);

		switch (command.toLowerCase()) {
		case "user":
			lastUser = dao.checkUser(param.trim());
			if (lastUser != null)
				return "Username correct. Enter password to login";
			else return param + " isn't conrrect. Enter again!";
		case "password":
			if (lastUser != null) {
				return dao.login(lastUser, param.trim());
			} else {
				return "No username. Please enter username!";
			}
		default:
			return "command not found";
		}
	}

	@Override
	public List<String> execute(String line) throws RemoteException {
		analysis(line);
		List<String> list = new ArrayList<>();
		switch (command.toLowerCase()) {
		case "getbyid":
			list = dao.getById(param.trim());
			if (!list.isEmpty() && list.size() == 1) {
				return list;
			}
			break;
		case "getbyname":
			list = dao.getByName(param.trim());
			if (!list.isEmpty()) {
				return list;
			}
			break;
		case "getbyage":
			list = dao.getByAge(param.trim());
			if (!list.isEmpty()) {
				return list;
			}
			break;
		case "getbyscore":
			list = dao.getByScore(param.trim());
			if (!list.isEmpty()) {
				return list;
			}
			break;
		default:
			list.add(new String("command is not found"));
		}
		return list;

	}

}
