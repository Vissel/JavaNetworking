package extention.IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercise1 {
	
	//save danh sach sinh vien
	//load danh sach sinh vien
	
	public static void save(List<Student> listSt, String fname) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(fname));
		
		dos.writeLong(listSt.size());
		for(Student st : listSt) {
			st.save(dos);
			
			dos.writeLong(st.listGrade.size());
			for(Grade g : st.listGrade) {
				g.save(dos);
			}
		}
		dos.close();
	}
	
	public static List<Student> load(String fname) throws IOException{
		List<Student> listSt = new ArrayList<Student>();
		DataInputStream dis = new DataInputStream(new FileInputStream(fname));
		
		Student st ;
		long count = dis.readLong();
		for(int index = 0 ; index < count ; index++) {
			st = new Student(dis.readInt(), dis.readUTF());
			
			long gradeNum = dis.readLong();
			for(int i =0 ; i < gradeNum; i++) {
				st.addGrade(dis.readUTF(), dis.readDouble());
			}
			listSt.add(st);
		}
		dis.close();
		
		return listSt;
	}
	
	public static void main(String[] args) throws IOException {
		String fname = "D:\\eclipse-workspace\\data-test - Copy\\listStudent1.txt";
		
		List<Student> listSt = new ArrayList<Student>();
//		Student st1 = new Student(111, "Nguyễn Ng�?c Thach"); st1.addGrade("Lập trình mạng", 10.0);
//		Student st2 = new Student(222, "Nguyễn Thị Ng�?c Hân");
//		Student st3 = new Student(333, "Nguyễn Minh Tùng"); st3.addGrade("LTCB", 9.0); st3.addGrade("HDH NC", 7.0); st3.addGrade("Lập trình mạng", 8.0);
//		listSt.add(st1); listSt.add(st2); listSt.add(st3);
//		
//		save(listSt, fname);
		
		listSt = load(fname);
		for(Student st : listSt) {
			System.out.println(st.toString());
		}
	}

}
