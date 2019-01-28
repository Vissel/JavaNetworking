package review.midtermexam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Ex15 {
/*
 * 16.	(6)Viết CT Import/Export danh sách sinh viên from/to text file với bảng mã cho trước. 
 * Export/Import dữ liệu này vào excel để xử lý và ngược lại
 */
	
	//export danh sach sv ra file text
	public static void export(List<Student2> listSt, String desFile) throws IOException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile), "UTF-8"));
		for(Student2 st : listSt) {
			out.println(st.export());
		}
		out.close();
	}
	//import file ra List, và ra file des
	public static List<Student2> importFile(String srcFile, String desFile) throws IOException{
		List<Student2> listSt = new ArrayList<>();
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "UTF-8"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile),"UTF-16"));
		
		Student2 st ;
		String line;
		while((line= in.readLine()) != null) {
			out.println(line);
			st = new Student2(); 
			st = st.importSt(line); // conver Student -> String để đọc từng dòng
			listSt.add(st);
		}
		in.close();
		out.close();
		return listSt;
	}
	
	public static void main(String[] args) throws IOException {
		List<Student2> listSt = new ArrayList<>();
//		listSt.add(new Student2(111, "Nguyễn Ngọc Trân Châu", 9.0));
//		listSt.add(new Student2(222, "Lê Thị Hồng Trà", 8.0));
//		listSt.add(new Student2(333, "Nguyễn Trần Pudding", 10.0));
		
		String srcFile = "D:\\eclipse-workspace\\data-test - Copy\\ex15.txt";
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\ex15(import).txt";
//		export(listSt, srcFile);
		listSt = importFile(srcFile,desFile);
		for(Student2 s : listSt) System.out.println(s.export());
		
	}

}
