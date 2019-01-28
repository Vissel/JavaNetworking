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

public class De11 {
	//save list student
	public void saveStudents(List<Student3> listSt, String desFile) throws IOException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile),"UTF-8"));
		for(Student3 st : listSt) {
			out.println(st.saveStudent());
		}
		out.close();
	}
	
	//load list student
	public List<String> load(String srcFile, String desFile) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "UTF-8"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(desFile),"UTF-8"));
		List<String> list = new ArrayList<>();
		Student3 st;
		String average;
		
		String line;
		while((line = in.readLine())!= null) {
			out.println(line);
			st = new Student3();
			average = st.caculator(line);
			list.add(average);
		}
		in.close();
		out.close();
		return list;
	}
	public static void main(String[] args) throws IOException {
//		Student3 st1 = new Student3(123, "Nguyễn Ngọc Thạch"); st1.addGrade("LTNC", 9.0); st1.addGrade("LTM", 10.0); st1.addGrade("HDT", 7.6);
//		Student3 st2 = new Student3(456, "Nguyễn Minh Tùng"); st2.addGrade("LTCB", 7.0); st2.addGrade("HDH", 9);
//		Student3 st3 = new Student3(678, "Tiểu Mỹ Thụ");
//		List<Student3> listSt = new ArrayList<>(); listSt.add(st1); listSt.add(st2); listSt.add(st3);
		String srcFile = "D:\\eclipse-workspace\\folder4\\listStudent.txt";
		
		De11 d11 = new De11();
		List<String> list = new ArrayList<>();
//		d11.saveStudents(listSt, desFile);
		String desFile = "D:\\eclipse-workspace\\folder4\\listStudent(load).txt";
		list = d11.load(srcFile, desFile);
		for(String str : list) System.out.println(str);
	}

}
