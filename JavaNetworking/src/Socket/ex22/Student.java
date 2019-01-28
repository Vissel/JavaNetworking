package Socket.ex22;

public class Student {
	int id;
	String name;
	int age;
	float grade;
	public Student(int id, String name, int age, float grade) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.grade = grade;
	}
	public Student() {}
	
	@Override
	public String toString() {
		return id + "\t" + name + "\t" + age + "\t" + grade;
	}
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	
	
}
