package review.midtermexam;

import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Grade {
	String name;
	double score;
	public Grade(String name, double score) {
		this.name = name;
		this.score = score;
	}
	public void saveGrade(DataOutput output) throws IOException {
		output.writeUTF(name);
		output.writeDouble(score);
	}
	public void saveGradeRaf(RandomAccessFile output) throws IOException {
		output.writeUTF(name);
		output.writeDouble(score);
	}
	@Override
	public String toString() {
		return "Grade [name=" + name + ", score=" + score + "]";
	}
}
