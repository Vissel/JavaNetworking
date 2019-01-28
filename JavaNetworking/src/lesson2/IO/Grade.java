package lesson2.IO;

import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Grade {
	 String name;
	 double score;
	public Grade(String name, double score) {
		super();
		this.name = name;
		this.score = score;
	}
	public Grade(){}	
	
	
	@Override
	public String toString() {
		return "Grade [name=" + name + ", score=" + score + "]";
	}
	public void saveGrade(RandomAccessFile dos) throws IOException {
		dos.writeUTF(name);
		dos.writeDouble(score);
	}
	public void loadGrade(RandomAccessFile input) throws IOException {
		input.readUTF();
		input.readDouble();
	}
	
}
