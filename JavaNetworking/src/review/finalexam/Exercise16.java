package review.finalexam;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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

public class Exercise16 {
	/*
	 * 16. (6)Viết CT Import/Export danh sách sinh viên from/to text file với bảng
	 * mã cho trước. Export/Import dữ liệu này vào excel để xử lý và ngược lại
	 */

	public static void main(String[] args) throws IOException {
//		Student s1 = new Student(111, "Nguyễn Ngọc Thạch", 10);
//		Student s2 = new Student(222, "Nguyễn Minh Tùng", 9);
//		Student s3 = new Student(333, "Dương Bích Ngọc", 9.5);
//		List<Student> listSt = new ArrayList<>();
//		listSt.add(s1);
//		listSt.add(s2);
//		listSt.add(s3);
		String srcFile = "D:\\eclipse-workspace\\data-test - Copy\\folder4\\export2.txt";
//		exportList(listSt, srcFile);
		
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder4\\import.txt";
		importList(srcFile, desFile);
	}

	// import
	public static void exportList(List<Student> listSt, String desFile)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile), "UTF-8"));

		for (Student st : listSt) {
			out.println(st.exportStudent());
		}
		out.close();
	}	

	// export from .txt to .xlsx
	public static void importList(String srcFile, String desFile) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "UTF-16"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile), "UTF-8"));
		String line ;
		while ((line = in.readLine()) != null) {
			out.println(line);
		}
		out.close();
		in.close();
	}
}
