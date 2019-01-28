package Socket.ex22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static List<Student> listSt = new ArrayList<Student>(); // vi tao de sd chung nen dung static
	private static List<User> listUser = new ArrayList<User>();
	
	public DAO() {
	}

	// static block
	static {
		listSt.add(new Student(1212, "Nguyễn Ngọc Thạch", 22, (float) 10.0));
		listSt.add(new Student(3434, "Nguyen Minh Tung", 21, (float)9));
		listSt.add(new Student(5656, "Nguyen Thi Ngoc Han", 21, (float)8.8));
		listUser.add(new User("admin", "admin"));
		listUser.add(new User("thach", "12345"));
	}
	
	
	
	public boolean checkUser(String param) {
		for (User u : listUser) {
			if (u.checkUser(param))
				return true;
		}
		return false;
	}

	public boolean checkLogin(String lastUser, String param) {
		for (User u : listUser) {
			if (u.checkLogin(lastUser, param))
				return true;
		}
		return false;
	}

	public String getID(String param) {
		for (int i = 0; i < listSt.size(); i++) {
			String findId = Integer.toString(listSt.get(i).getId());
			if (param.equals(findId.toString())) {
				return listSt.get(i).toString();
			}
		}
		return null;
	}

	public static List<String> getNAME(String param) {
		List<String> list = new ArrayList<String>();
		for(Student st : listSt) {
			String findName = st.getName().toLowerCase();
			if(findName.contains(param.toLowerCase())) {
				list.add(st.toString());
			}
		}
		return list;
	}

	public List<String> getAGE(String param) {
		List<String> list = new ArrayList<String>();
		for(Student st : listSt) {
			String findAge = Integer.toString(st.getAge());
			if(findAge.equals(param)) {
				list.add(st.toString());
			}
		}
		return list;
	}

	public List<String> getGRADE(String param) {
		List<String> list = new ArrayList<String>();
		for(Student st : listSt) {
			String findGrade = Double.toString(st.getGrade());
			if(findGrade.equals(param)) {
				list.add(st.toString());
			}
		}
		return list;
	}
	
}
