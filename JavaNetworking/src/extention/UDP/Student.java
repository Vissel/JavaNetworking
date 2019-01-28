package extention.UDP;

public class Student {
	int id;
	String name;
	double grade;
	public Student(int id, String name, double grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
	}
	public String toString() {
		return id + "\t" + name + "\t" + grade;
	}
}
