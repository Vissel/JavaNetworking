package extention.IO;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Grade {
	String name;
	double score;
	public Grade(String name, double score) {
		super();
		this.name = name;
		this.score = score;
	}
	public Grade() {}
	
	public void save(DataOutput dos) throws IOException {
		dos.writeUTF(name);
		dos.writeDouble(score);
	}
	public void load(DataInput dis) throws IOException{
		dis.readUTF();
		dis.readDouble();
	}
	@Override
	public String toString() {
		return "Grade:" + name + ";" + score ;
	}
	
}
