package extention.IO;

import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Student {
	int id;
	String name;
	List<Grade> listGrade;
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
		listGrade = new ArrayList<Grade>();
	}
	public Student() {}
	
	public void addGrade(String name, double score) {
		listGrade.add(new Grade(name,score));
	}
	
	//save student
	public void save(DataOutput dos) throws IOException {
		dos.writeInt(id);
		dos.writeUTF(name);
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
		
		return "Student [id=" + id + ", name=" + name + ", \tlistGrade=" + listGrade + "]";
	}
	
	
}
