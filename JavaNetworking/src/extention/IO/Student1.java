package extention.IO;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Student1 {
	int id;
	String name;
	List<Grade> listGrade;
	
	public Student1() {}
	public Student1(int id, String name) {
		this.id = id;
		this.name = name;
		listGrade = new ArrayList<Grade>();
	}
	public void addGrade(String name, double score) {
		listGrade.add(new Grade(name,score));
	}
	public void save(DataOutput dos) throws IOException {
		dos.writeInt(id);
		dos.writeUTF(name);
		
		dos.writeInt(listGrade.size());
		for(int i = 0; i< listGrade.size(); i++) {
			listGrade.get(i).save(dos);
		}
	}
	public void getStudent(DataInput dis) throws IOException {
		dis.readInt();
		dis.readUTF();
		
		int countGrade = dis.readInt();
		Grade g;
		for(int i = 0; i < countGrade; i++) {
			g = new Grade();
			g.load(dis);
			listGrade.add(g);
		}
	}
	@Override
	public String toString() {
		return id + "\t" + name + "\t" + listGrade ;
	}
	
	
}
