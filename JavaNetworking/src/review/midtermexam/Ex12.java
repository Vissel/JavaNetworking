package review.midtermexam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ex12 {
	/*
	 * 12. (3)Viết CT lưu/Đọc danh sách sinh viên xuống file nhị phân (Lưu từng
	 * thuộc tính): Danh sách sinh viên bất kỳ; mỗi sinh viên có danh sách điểm bất
	 * kỳ
	 */

	// save
	public void saveStudents(List<Student> listSt, String desFile) throws IOException {
		DataOutputStream output = new DataOutputStream(new FileOutputStream(desFile));

		output.writeInt(listSt.size());// luu size cua List student
		for (Student st : listSt) {
			st.saveStudent(output);
		}
		output.close();
	}

	// load
	public List<Student> loadStudents(String srcFile) throws IOException {
		List<Student> list = new ArrayList<>();
		DataInputStream input = new DataInputStream(new FileInputStream(srcFile));
		
		Student st ;
		int sizeSt = input.readInt();//doc size list student
		for(int i =0 ; i < sizeSt; i++) {
			st = new Student(input.readInt(), input.readUTF());
			
			int sizeGrade = input.readInt();
			for(int j = 0 ; j < sizeGrade; j++) {
				st.addGrade(input.readUTF(), input.readDouble());
			}
			list.add(st);
		}
		input.close();
		return list;
	}

	public static void main(String[] args) throws IOException {
//		Student st1 = new Student(11111, "Nguyễn Ngọc Thạch");
//		st1.addGrade("LTNC", 7.2);
//		st1.addGrade("LTM", 10.0);
//		st1.addGrade("Đồ họa máy tính", 9.0);
//		Student st2 = new Student(222, "Nguyễn Thị Anh Đào");
//		st2.addGrade("LTCB", 8.1);
//		Student st3 = new Student(3333, "Hồ Đá");
//		st3.addGrade("HDH", 4.0);
//		st3.addGrade("TKHDT", 6.1);
//		List<Student> listSt = new ArrayList<>();
//		listSt.add(st1);
//		listSt.add(st2);
//		listSt.add(st3);
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder1\\text2.txt";
		Ex12 saveLoad = new Ex12();
//		saveLoad.saveStudents(listSt, desFile);
		List<Student> list = saveLoad.loadStudents(desFile);
		for(Student st : list) System.out.println(st.toString());
	}

}
