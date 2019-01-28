package lesson2.IO;

import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Student {
	 int id;
	 String name;
	 List<Grade> listGrade;
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.listGrade = new ArrayList<Grade>();
	}
	public Student() {}
	
	public void addGrade(String name, double score) {
		listGrade.add(new Grade(name, score));
	}
	
	//viet lai cho toi uu, reuse
	//tinh uy quyen
	public void save(RandomAccessFile dos) throws IOException {
		dos.writeInt(id);
		dos.writeUTF(name);
		
		dos.writeLong(listGrade.size());
		for(int i =0 ; i < listGrade.size(); i++) {
			listGrade.get(i).saveGrade(dos);
		}
	}
	

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ",\t listGrade=" + listGrade + "]";
	}
	public void loadStudent(RandomAccessFile input) throws IOException {
		input.readInt();
		input.readUTF();
		
		long countGrade = input.readLong();
		Grade g ;
		for(int i =0 ; i < countGrade ;i++) {
			g = new Grade();
			g.loadGrade(input);
			listGrade.add(g);
		}
	}
	
	
	
}
