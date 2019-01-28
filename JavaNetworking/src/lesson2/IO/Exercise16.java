package lesson2.IO;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Exercise16 {
	//Homework: tach1 ra danh sach diem va danh sach sinh vien
	
	
	//export, import List<Student>
	/*Moi sv la 1 line 
	 * hướng kí tự, bảng mã. outputstreamwriter , printwriter
	 * các truong ngăn cách nhau bởi dấu tab*/
	public static void export(List<Student16> los, String fname) throws IOException {
		
		 PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fname), "UTF-8"));
		 
		 for(Student16 st : los) {
			 output.println(st.export());
		 }
		 output.close();
	}
	/*Chữ ko đúng chỉ có 1 lí do là bảng mã ko đúng
	 * tách dc các trường dữ liệu ra
	 * unicode text la utf-16 */
	public static List<Student16> importSt(String fname) throws IOException{
		//test thử đọc từng dòng lên console
		//Dùng slipt (regularExpression) or dùng StringTokenizer 
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fname), "UTF-16")); // do là kiểu unicode
		List<Student16> los = new ArrayList<Student16>();
		
		String line ;
		
		while((line = input.readLine()) != null) {
			Student16 st= new Student16();
//			System.out.println(line);
			st.importSt(line);
			los.add(st);
		}
		
		input.close();
		return los;
	}
	//replaceAll(cũng dùng regularExpression)
	//compline 
	
	public static void main(String[] args) throws IOException {
		String fname = "D:\\eclipse-workspace\\data-test - Copy\\text3333.txt";
		List<Student16> los = new ArrayList<Student16>();
//		
//		los.add(new Student16(111, "Nguyễn Ngọc Thạch", 7.5));
//		los.add(new Student16(222, "Nguyễn Ngoc Hân", 9.1));
//		los.add(new Student16(333, "Nguyễn Minh Tùng", 8.0));
		
//		export(los, fname);
		los = importSt(fname);
		for(Student16 st : los) {
			System.out.println(st.id + "\t"+st.name+"\t"+st.grade);
		}
	}

}
