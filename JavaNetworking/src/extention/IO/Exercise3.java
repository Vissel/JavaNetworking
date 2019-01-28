 package extention.IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercise3 {

	public static void save(List<Student1> listSt, String fname) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(fname));
		
		dos.writeInt(listSt.size());
		for(Student1 st : listSt) {
			st.save(dos);
		}
		
		dos.close();
	}
	
	public static List<Student1> loadAll(String fname) throws IOException{
		List<Student1> listSt = new ArrayList<Student1>();
		DataInputStream dis = new DataInputStream(new FileInputStream(fname));
		Student1 st ;
		int countGrade;
		
		int countSt = dis.readInt();
		for(int index = 0; index < countSt; index ++) {
			st = new Student1(dis.readInt(), dis.readUTF());
			
			countGrade = dis.readInt();
			for(int i = 0 ; i < countGrade; i++) {
				st.addGrade(dis.readUTF(), dis.readDouble());
			}
			listSt.add(st);
		}
		dis.close();
		return listSt;
	}
	public static void main(String[] args) throws IOException {
		List<Student1> listSt = new ArrayList<Student1>();
//		Student1 st1= new Student1(123, "Nguyễn Ng�?c Thạch"); st1.addGrade("LTM", 8.0); st1.addGrade("LTNC", 7.0); st1.addGrade("HDH", 10.0);
//		Student1 st2 = new Student1(456, "Nguyễn Phương Nam"); 
//		Student1 st3 = new Student1(789, "Lý Văn Hoa"); st3.addGrade("HDH", 5.0);
//		listSt.add(st1); listSt.add(st2); listSt.add(st3);
		
		String fname = "D:\\eclipse-workspace\\data-test - Copy\\D1.txt";
//		save(listSt, fname);
		
		listSt = loadAll(fname);
		for(Student1 st : listSt) {
			System.out.println(st.toString());
			
		}
		
	}

}
