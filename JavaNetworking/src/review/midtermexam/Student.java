package review.midtermexam;

import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Student {
	int id;
	String name;
	List<Grade> grades;
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
		grades = new ArrayList<>();
	}
	public Student() {}
	
	public void addGrade(String name, double score) {
		grades.add(new Grade(name, score));
	}
	public void saveStudent(DataOutput output) throws IOException {
		output.writeInt(id);
		output.writeUTF(name);
		
		output.writeInt(grades.size());
		for(Grade g : grades) {
			g.saveGrade(output);
		}
		
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", grades=" + grades + "]";
	}
	
	public void saveStudentRaf(RandomAccessFile output) throws IOException {
		output.writeInt(id);
		output.writeUTF(name);
		
		output.writeInt(grades.size());
		for(Grade g : grades) {
			g.saveGradeRaf(output);
		}
		
	}
	public Student loadStudent(RandomAccessFile raf) throws IOException {
		Student st = new Student(raf.readInt(), raf.readUTF());
		
		int sizeGrade = raf.readInt();
		for(int i = 0 ; i < sizeGrade; i++) {
			st.addGrade(raf.readUTF(), raf.readDouble());
		}
		return st;
	}
	
}
