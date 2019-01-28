package review.midtermexam;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Ex13 {
/*
 * 13.	(4) Cho Danh sách sinh viên bất kỳ; mỗi sinh viên có danh sách điểm bất kỳ. 
 * Viết phương thức lưu danh sách sinh viên này xuống file sao cho có thể load sinh viên thứ n bất kỳ
 *  mà không phải load thông tin của n-1 sinh viên trước đó (dùng RAF)
 */
	
	public void saveStudent(List<Student> listSt, String desFile) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(desFile, "rw");
		
		raf.writeLong(listSt.size());//luu size cua list student
		for(Student st : listSt) raf.writeLong(0); // danh dau vi tri cua tung student
		
		long current;
		for(int index = 0; index < listSt.size(); index++) {
			current = raf.getFilePointer();
			raf.seek(index * 8+8);// di chuyen lai vi tri đánh dấu đầu tiên
			raf.writeLong(current); // ghi nơi lưu vào vị trí đó 
			raf.seek(current);// tro lai vi  tri current
			listSt.get(index).saveStudentRaf(raf);
		}
		raf.close();
	}
	
	public Student getStudent(String srcFile, long number) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(srcFile, "rw");
		Student st = new Student();
		
		raf.readLong(); //đọc size list
		raf.seek(number*8); // di chuyen lai vi tri đánh dấu
		long locate = raf.readLong(); // đoc nơi lưu
		raf.seek(locate);
		st = st.loadStudent(raf);
		
		raf.close();
		return st;
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
		String desFile= "D:\\eclipse-workspace\\data-test - Copy\\folder2\\Student.txt";
		
		Ex13 getOneStudent = new Ex13();
//		getOneStudent.saveStudent(listSt, desFile);
		Student st = getOneStudent.getStudent(desFile, 3);
		System.out.println(st);
	}

}
