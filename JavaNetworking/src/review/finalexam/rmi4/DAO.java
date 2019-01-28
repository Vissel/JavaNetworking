package review.finalexam.rmi4;

import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static List<Student> listSt = new ArrayList<Student>();
	private static List<Account> listAcc = new ArrayList<Account>();

	public static void main(String[] args) {
		DAO d = new DAO();
		List<String> list = d.getById("123");
		if (!list.isEmpty()) {
			System.out.println(list.size());
		} else
			System.out.println("empty");

	}

	static {
		Student st1 = new Student(111, "Nguyen Ngoc Thach", 23);
		st1.addScore("LTM", 10);
		st1.addScore("DHMT", 7);
		Student st2 = new Student(222, "Nguyen Minh Tung", 24);
		st2.addScore("Testing", 10);
		Student st3 = new Student(333, "Ngoc Trinh", 25);
		listSt.add(st1);
		listSt.add(st2);
		listSt.add(st3);
		listAcc.add(new Account("thach", "thach"));
		listAcc.add(new Account("ngoc", "11111"));
	}

	public String login(String lastUser, String param) {
		for (Account acc : listAcc) {
			if (acc.getUsername().equals(lastUser) && acc.getPassword().equals(param))
				return "login success!";
		}
		return null;
	}

	public String checkUser(String param) {
		for (Account acc : listAcc) {
			if (acc.getUsername().equals(param))
				return acc.getUsername();
		}
		return null;
	}

	public List<String> getById(String param) {
		List<String> list = new ArrayList<>();
		for (Student st : listSt) {
			if (st.getId() == Integer.parseInt(param))
				list.add(st.toString());
		}
		return list;
	}

	public List<String> getByName(String param) {
		List<String> list = new ArrayList<>();
		for (Student st : listSt) {
			if (st.getName().toLowerCase().contains(param)) list.add(st.toString());
		}
		return list;
	}

	public List<String> getByAge(String param) {
		List<String> list = new ArrayList<>();
		for (Student st : listSt) {
			if (st.getAge() == Integer.parseInt(param))
				list.add(st.toString());
		}
		return list;
	}

	public List<String> getByScore(String param) {
		List<String> list = new ArrayList<>();
		for (Student st : listSt) {
			for(Score score : st.getListScore()) {
				if(score.getScore() == Double.parseDouble(param)) list.add(st.toString());
			}
		}
		return list;
	}

}
