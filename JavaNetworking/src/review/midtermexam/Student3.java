package review.midtermexam;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Student3 {
	int id;
	String name;
	List<Grade> grades;

	public Student3(int id, String name) {
		this.id = id;
		this.name = name;
		grades = new ArrayList<>();
	}

	public Student3() {
	}

	public void addGrade(String name, double score) {
		grades.add(new Grade(name, score));
	}

	public String saveStudent() {
		return id + "\t" + name + "\t" + saveGrade();
	}

	public String saveGrade() {
		String res = "";
		for (Grade g : grades) {
			res += g.name + "\t" + g.score + "\t";
		}
		return res;
	}

	public String caculator(String line) {
		StringTokenizer token = new StringTokenizer(line, "\t");
		int count = (token.countTokens() - 2); // đếm xem có bao nhiêu môn có điểm
		id = Integer.parseInt(token.nextToken());
		name = token.nextToken();

//		System.out.println(count);
		if (count == 0)
			return id + "\t" +"0";

		else {
			String temp;
			List<Double> scores = new ArrayList<>();
			for (int i = 1; i <= count; i++) {
				temp = token.nextToken();
				if (i % 2 == 0) { // tách lấy những phần điểm, i là số chẵn thì nó là điểm
					scores.add(Double.parseDouble(temp));
				}
			}
			double sum = 0, average;
			for (Double d : scores) {
				sum += d;
			}
			average = sum / scores.size();
			return id + "\t" + average;
		}
	}
//	public static void main(String[] args) {
//		Student3 s = new Student3();
//		s.caculator("111\tThach");
//	}
}
