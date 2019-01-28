package review.finalexam;

import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Grade {
	private String name;
	private double grade;
	public Grade(String name, double grade) {
		super();
		this.name = name;
		this.grade = grade;
	}
	public Grade() {}
	
	//save grade
	public void saveGrade(DataOutput dos) throws IOException {
		dos.writeUTF(name);
		dos.writeDouble(grade);
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
		return "Grade [name=" + name + ", grade=" + grade + "]";
	}
	
	
}
