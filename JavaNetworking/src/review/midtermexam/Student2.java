package review.midtermexam;

import java.util.StringTokenizer;

public class Student2 {
	int id;
	String name;
	double grade;
	public Student2(int id, String name, double grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
	}
	public Student2() {}
	
	
	public String export() {
		return id + "\t" + name + "\t" +grade;
	}
	public Student2 importSt(String line) {
		Student2 st = new Student2();
		StringTokenizer token = new StringTokenizer(line, "\t");
		id = Integer.parseInt(token.nextToken());
		name = token.nextToken();
		grade = Double.parseDouble(token.nextToken());
//		System.out.println(id2 + name2 + grade2);
		st.setId(id); st.setName(name); st.setGrade(grade);
		return st;
	}
//	public static void main(String[] args) {
//		importSt("1\tNgoc\t2.0");
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Student2 [id=" + id + ", name=" + name + ", grade=" + grade + "]";
	}
	
}
