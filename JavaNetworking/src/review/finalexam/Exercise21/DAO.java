package review.finalexam.Exercise21;

import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static List<Student> listSt = new ArrayList<Student>();

	public  DAO() {}
	static {
		Student st1 = new Student(111, "Nguyen Ngoc Thach", 23);
		st1.addScore("LTM", 10);
		st1.addScore("DHMT", 7);
		Student st2 = new Student(222, "Nguyen Minh Tugn", 24);
		st2.addScore("Testing", 10);
		Student st3 = new Student(333, "Ngoc Trinh", 25);
		listSt.add(st1);
		listSt.add(st2);
		listSt.add(st3);
	}

	public static void main(String[] args) {
//		DAO d = new DAO();
//		List<String> list = d.getByName("NGUYEN NGOC THACH");
//		if (!list.isEmpty()) {
//			for (String s : list)
//				System.out.println(s);
//		} else
//			System.out.println("list empty");
		String a ="nguyen ngoc thach";
		String b = " THACH";
		if(a.toUpperCase().contains(b.toUpperCase())) System.out.println("true");
		else System.out.println("false");
	}

	public List<String> getByName(String param) {
		List<String> list = new ArrayList<String>();
		for (Student st : listSt) {
			String nameSt = st.getName().toUpperCase();
			if (nameSt.contains(param.toUpperCase())) {
				list.add(st.toString());
			}
		}
		return list;
		
	}

	public List<String> getByAge(String param) {
		List<String> list = new ArrayList<String>();
		for (Student st : listSt) {
			if (st.getAge() == Integer.parseInt(param)) {
				list.add(st.toString());
			}
		}
		return list;
	}

	public List<String> getByScore(String param) {
		List<String> list = new ArrayList<String>();
		for (Student st : listSt) {
			for(Score score : st.getListScore()) {
				if(score.getScore() == Double.parseDouble(param)) list.add(st.toString());
			}
		}
		return list;
	}

}
