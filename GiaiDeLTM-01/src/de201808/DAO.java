package de201808;

import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static List<User> listUser = new ArrayList<User>();
	public static void main(String[] args) {
		DAO d = new DAO();
		System.out.println(d.login("thach", "thach"));
	}

	static{
		listUser.add(new User("thach", "thach"));
		listUser.add(new User("tung", "12345"));
	}

	public String checkUser(String param) {
		for(User u : listUser){
			if(u.getUsername().equals(param)) return u.getUsername();
		}
		return null;
	}
	public boolean login(String lastUser, String param) {
		for(User u : listUser){
			if(u.getUsername().equals(lastUser) && u.getPassword().equals(param)) return true;
		}
		return false;
	}
	
}
