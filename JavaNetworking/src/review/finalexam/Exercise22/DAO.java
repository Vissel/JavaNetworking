package review.finalexam.Exercise22;

import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static List<Student> listSt = new ArrayList<Student>();
	private static List<Account> listAcc = new ArrayList<Account>();

	public static void main(String[] args) {
		DAO d = new DAO();
//		String str = d.checkUsername("thacch");
//		boolean str = d.checkLogin("thach", "11111");
		List<Student> str = d.findByName("Nguyen  ");
		System.out.println(str.toString());
	}

	static {
		listSt.add(new Student(123, "Nguyen Ngoc Thach", 23, 10.0));
		listSt.add(new Student(456, "Nguyen Thong Minh", 22, 9.5));
		listSt.add(new Student(789, "Nguyen Thông Thái", 21, 9));
		listAcc.add(new Account("thach", "thach"));
		listAcc.add(new Account("ngoc", "11111"));
	}

	public String checkUsername(String par) {
		for (Account user : listAcc) {
			if (user.getUsername().equals(par))
				return par;
		}
		return null;
	}

	public boolean checkLogin(String lastUser, String par) {
		for(Account acc : listAcc) {
			if(acc.getUsername().equals(lastUser) && acc.getPassword().equals(par)) return true;
		}
		return false;
	}

	public String findSTbyID(String param) {
		for(Student st : listSt) {
			if(st.getId() == Integer.parseInt(param)) return st.toString();
		}
		return null;
	}

	public List<Student> findByName(String param) {
		List<Student> list = new ArrayList<Student>();
		for(Student st : listSt) {
			if(st.getName().toLowerCase().contains(param.toLowerCase())) {
				list.add(st);
			}
		}
		return list;
	}
	public List<Student> findByAge(String param) {
		List<Student> list = new ArrayList<Student>();
		for(Student st : listSt) {
			if(st.getAge() == Integer.parseInt(param)) {
				list.add(st);
			}
		}
		return list;
	}
	public List<Student> findByScore(String param) {
		List<Student> list = new ArrayList<Student>();
		for(Student st : listSt) {
			if(st.getScore() == Double.parseDouble(param)) {
				list.add(st);
			}
		}
		return list;
	}
}
