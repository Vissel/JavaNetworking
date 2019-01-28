package lesson2.IO;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintWriter;

public class Student16 {
	int id;
	String name;
	double grade;
	
	public Student16() {}
	
	public Student16(int id, String name,double grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
	}
	
	public String export() {
		return id +"\t"+name+"\t"+grade;
		} 
	public void importSt(String line) throws IOException {
		String[] str = line.split("\t");
		this.id = Integer.parseInt(str[0]);
		this.name = str[1];
		this.grade = Double.parseDouble(str[2]);
	}
}
