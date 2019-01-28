package lesson2.IO;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Exercise13 {

	public static void save(List<Student> listSt, String fname) throws IOException {
		RandomAccessFile output = new RandomAccessFile(fname, "rw");
		
		//đánh dấu vị trí các student
		output.writeLong(listSt.size());
		for(Student st : listSt) output.writeLong(0);
		
		long current;
		for(int index =0; index < listSt.size(); index++) {
			current = output.getFilePointer();
			output.seek(index*8);
			output.writeLong(current);
			output.seek(current);
			listSt.get(index).save(output);
		}
		output.close();
	}
	
	public static Student getStudent(String fname, long index) throws IOException {
		RandomAccessFile input = new RandomAccessFile(fname, "rw");
		
		long size = input.readLong();
		if(index < -1 || index >= size) {
			input.close();
			return null;
		}
		
		Student st = new Student();
		
		input.seek(index*8);
		long locate = input.readLong();
		input.seek(locate);
		st.loadStudent(input);
		
		input.close();
		return st;
	}
	
	public static void main(String[] args) throws IOException {
		//create Student
//		Student st1 = new Student(1, "Nguyễn Ngọc Thạch");
//		st1.addGrade("LTM", 6);
//		Student st2 = new Student(2, "Lê Thị Mỹ Duyên");
//		st2.addGrade("LTCB",7);
//		st2.addGrade("HDH", 5);
//		Student st3 = new Student(3	, "Lương Thị Hường");	
//		
//		List<Student> listStudent = new ArrayList<Student>();
//		listStudent.add(st1); listStudent.add(st2); listStudent.add(st3);
		
		String fname = "D:\\eclipse-workspace\\data-test - Copy\\list11.txt";
		
//		save(listStudent, fname);
		getStudent(fname, 0).toString();
		
	}

}
