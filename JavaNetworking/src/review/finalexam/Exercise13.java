package review.finalexam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Exercise13 {
/*13.	(4) Cho Danh sách sinh viên bất kỳ; mỗi sinh viên có danh sách điểm bất kỳ. 
 * Viết phương thức lưu danh sách sinh viên này xuống file sao cho có thể load sinh viên thứ n bất kỳ 
 * mà không phải load thông tin của n-1 sinh viên trước đó (dùng RAF)*/
	
	public static void main(String[] args) throws IOException {
//		Student st1 = new Student(1, "Nguyễn Minh Tùng");
//		st1.addGrade("LTM", 10);
//		st1.addGrade("DHMT", 5);
//		st1.addGrade("PTTK", 9);
//		Student st2 = new Student(2, "Nguyễn Thị Hoa Hướng Dương");
//		st2.addGrade("LTM", 9);
//		st2.addGrade("Test", 8);
//		Student st3 = new Student(3, "Trần Kim Thoa");
//		List<Student> listSt = new ArrayList<>();
//		listSt.add(st1);
//		listSt.add(st2);
//		listSt.add(st3);
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder3\\listRaf.txt";
//		
//		saveListStudent(listSt, desFile);
		
		Student st = loadStudent(desFile, 2);
		System.out.println(st.toString());
	}

	
	//save
	public static void saveListStudent(List<Student> listSt, String desFile) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(desFile, "rw");
		
		raf.writeInt(listSt.size()); //save size listSt 
		for(Student st : listSt) {
			raf.writeLong(0);// danh dau = 0
		}
		
		for(int index = 0; index < listSt.size(); index ++) {
			long currentPointer = raf.getFilePointer(); //current pointer
			raf.seek(index*8); //move to position with index
			raf.writeLong(currentPointer); //write index
			raf.seek(currentPointer); //comback current 
			listSt.get(index).saveStudent(raf);
		}
		
		raf.close();
	}
	
	//load
	public static Student loadStudent(String srcFile, int index) throws IOException {
		Student student = new Student();
		RandomAccessFile raf = new RandomAccessFile(srcFile, "rw");
		
		int sizeList = raf.readInt();//read size listSt
//		long position = index*
		raf.seek(index*8);
		long position = raf.readLong(); //doc du lieu
		raf.seek(position);
		student.loadStudent(raf);
		raf.close();
		return student;
	}
}
