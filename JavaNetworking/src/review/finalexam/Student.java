package review.finalexam;

import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Student {
	private int id;
	private String name;
	private List<Grade> listGrade;
	
	//sd bai 16
	private double grade;

	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		listGrade = new ArrayList<>();
	}

	public Student() {
		listGrade = new ArrayList<>();
	}

	//bai 16
	public Student(int id, String name, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
	}

	// add grade into list
	public void addGrade(String name, double grade) {
		listGrade.add(new Grade(name, grade));
	}

	//save Student	
	public void saveStudent(DataOutput dos) throws IOException {
		dos.writeInt(id);
		dos.writeUTF(name);
		
		dos.writeInt(listGrade.size()); //save size listGrade
		for(int j=0; j < listGrade.size(); j++) {
			listGrade.get(j).saveGrade(dos);
		}
	}
	
	//load student: bai 13
	public Student loadStudent(RandomAccessFile raf) throws IOException {
		this.setId(raf.readInt());
		this.setName(raf.readUTF());
		
		int sizelistGrade = raf.readInt();
		for(int i = 0; i< sizelistGrade; i++) {
			this.addGrade(raf.readUTF(), raf.readDouble());
		}
		return this;
	}

	// bai 16
	public String exportStudent() {
		return id + "\t" + name + "\t" + grade;
	}
	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
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

	public List<Grade> getListGrade() {
		return listGrade;
	}

	public void setListGrade(List<Grade> listGrade) {
		this.listGrade = listGrade;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", listGrade=" + listGrade + "]";
	}

	


}
