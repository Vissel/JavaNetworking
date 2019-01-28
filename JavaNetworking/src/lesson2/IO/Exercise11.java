package lesson2.IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class Exercise11 {
	/*Viết chương trình lưu và đọc danh sách sinh viên xuống file nhị phân*/
	
	public static void saveListStudent(List<Student> los, String fname) throws IOException {
		File file = new File(fname);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		DataOutputStream dataOutput = new DataOutputStream(bos);
		
		//doc so luong sv
		dataOutput.writeInt(los.size());
		for (Student st: los) {
			dataOutput.writeInt(st.id);
			dataOutput.writeUTF(st.name);
			
			//doc so luong grade
			dataOutput.writeInt(st.listGrade.size());
			for (Grade g : st.listGrade) {
				dataOutput.writeUTF(g.name);
				dataOutput.writeDouble(g.score);
			}
		}
		
		dataOutput.close();
		bos.close();
	}
	
	public static List<Student> load(String fname) throws IOException{
		List<Student> listStudent = new ArrayList<Student>();
		File file = new File(fname);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		DataInputStream dataInput = new DataInputStream(bis);
		
		int count = dataInput.readInt();
		Student st;
		for(int i = 0; i < count ; i++) {
			st = new Student(dataInput.readInt(), dataInput.readUTF());
			
			int gNumber = dataInput.readInt();
			for(int j = 0 ; j < gNumber; j++) {
				st.addGrade(dataInput.readUTF(), dataInput.readDouble());
			}
			listStudent.add(st);
		}
		
		dataInput.close();
		bis.close();
		return listStudent;
	}
	
	public static void main(String[] args) throws IOException {
		//create List<Grade>
//		Grade g1 = new Grade("LTM", 6);	Grade g2 = new Grade("LTCB",7);	Grade g3 = new Grade("HDH", 5);
//		Grade g4 = new Grade("LTM",4);	Grade g5 = new Grade("LTCB", 3);Grade g6 = new Grade("HDH",0);
//		Grade g7 = new Grade("LTM",0);	Grade g8 = new Grade("LTCB", 0);Grade g9 = new Grade("HDH",0);
//		List<Grade> lgrade1 = new ArrayList<>(); lgrade1.add(g1); lgrade1.add(g2); lgrade1.add(g3);
//		List<Grade> lgrade2 = new ArrayList<>(); lgrade2.add(g4); lgrade2.add(g5); lgrade2.add(g6);
//		List<Grade> lgrade3 = new ArrayList<>(); lgrade3.add(g7); lgrade3.add(g8); lgrade3.add(g9);
		
		//create Student
//		Student st1 = new Student(1, "Thach");
//		st1.addGrade("LTM", 6);
//		Student st2 = new Student(2, "Nguyen");
//		st2.addGrade("LTCB",7);
//		st2.addGrade("HDH", 5);
//		Student st3 = new Student(3	, "Ngoc");	
		
		List<Student> listStudent = new ArrayList<Student>();
//		listStudent.add(st1); listStudent.add(st2); listStudent.add(st3);
		
		String fname = "D:\\eclipse-workspace\\data-test - Copy\\listStudent.txt";
		
//		saveListStudent(listStudent, fname);
		listStudent = load(fname);
		for(Student s : listStudent) System.out.println(s);
	}

}
