package extention.IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Exercise2 {
	
	//save danh sach sinh vien
	//load len 1 nguoi sinh vien da chi dinh . dung RandomAccessFile
	
	public static void save(List<Student1> listSt, String fname) throws IOException {
		RandomAccessFile output = new RandomAccessFile(fname, "rw");
		
		//duyet qua het list, luu moi vi tri la so 0
		//purpose: sau nay se get vi tri do ra
		output.writeLong(listSt.size());
		for(Student1 st : listSt) {
			output.writeLong(0);
		}
		
		long currentPointer;
		for(int index =0; index < listSt.size(); index++) {
			currentPointer = output.getFilePointer();
			output.seek(index*8);
			output.writeLong(currentPointer);
			output.seek(currentPointer);
			listSt.get(index).save(output);
		}
		output.close();
	}
	
	public static Student1 getStudent(String fname, long index) throws IOException {
		
		RandomAccessFile input = new RandomAccessFile(fname, "rw");
		
		long countSt = input.readLong();
		if(index < -1 || index >= countSt) {
			input.close();
			return null;
		}
		Student1 st = new Student1();
		input.seek(index*8);// seek tới vị trí mong muốn
		input.seek(input.readLong()); // seek tới vị trí lưu thông tin Student
		st.getStudent(input);
		
		input.close();
		
		return st;
	}
	
	public static void main(String[] args) throws IOException {
//		List<Student1> listSt = new ArrayList<Student1>();
//		Student1 st1= new Student1(123, "Nguyễn Ng�?c Thạch"); st1.addGrade("LTM", 8.0); st1.addGrade("LTNC", 7.0); st1.addGrade("HDH", 10.0);
//		Student1 st2 = new Student1(456, "Nguyễn Phương Nam"); 
//		Student1 st3 = new Student1(789, "Lý Văn Hoa"); st3.addGrade("HDH", 5.0);
//		listSt.add(st1); listSt.add(st2); listSt.add(st3);
		
		String fname = "D:\\eclipse-workspace\\data-test - Copy\\D2.txt";
//		save(listSt, fname);
		Student1 st = getStudent(fname, 0);
		System.out.println(st.toString());
	}

}
