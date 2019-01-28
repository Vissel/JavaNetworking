package review.finalexam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercise12 {
	/*
	 * 12. (3)Viết CT lưu/Đọc danh sách sinh viên xuống file nhị phân (Lưu từng
	 * thuộc tính): Danh sách sinh viên bất kỳ; mỗi sinh viên có danh sách điểm bất
	 * kỳ
	 */

	public static void main(String[] args) throws IOException {
//		Student st1 = new Student(1, "Nguyễn Ngọc Thạch");
//		st1.addGrade("LTM", 10);
//		st1.addGrade("DHMT", 5);
//		st1.addGrade("PTTK", 9);
//		Student st2 = new Student(2, "Nguyễn Thị Hoa Hồng");
//		st2.addGrade("LTM", 9);
//		st2.addGrade("Test", 8);
//		Student st3 = new Student(3, "Trần Lệ Như Hoa");
//		List<Student> listSt = new ArrayList<>();
//		listSt.add(st1);
//		listSt.add(st2);
//		listSt.add(st3);
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder3\\listStudent.txt";
//		saveListStudent(listSt, desFile);
		List<Student> list = readListStudent(desFile);
		for(Student st : list) System.out.println(st.toString());
	}

	// save
	public static void saveListStudent(List<Student> listSt, String desFile) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(desFile));

		dos.writeInt(listSt.size()); // size listSt
		for (int i = 0; i < listSt.size(); i++) {
			listSt.get(i).saveStudent(dos);
		}
		dos.close();
	}

	// read on console
	public static List<Student> readListStudent(String srcFile) throws IOException {
		List<Student> listSt = new ArrayList<>();
		DataInputStream dis = new DataInputStream(new FileInputStream(srcFile));
		Student st;
		int listSize = dis.readInt(); // read size listSt
		for (int i = 0; i < listSize; i++) {
			st = new Student(dis.readInt(), dis.readUTF());
			int listGrade = dis.readInt(); //read size Grade
			for(int j = 0 ; j < listGrade; j ++) {
				st.addGrade(dis.readUTF(), dis.readDouble());
			}
			listSt.add(st);
		}
		dis.close();
		return listSt;
	}
}
