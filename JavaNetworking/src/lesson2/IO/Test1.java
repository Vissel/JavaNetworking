package lesson2.IO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class Test1 {
	
	public static void main(String[] args) throws IOException {
		String path = "D:\\eclipse-workspace\\data-test - Copy\\D2utf8.txt";
		String text1 = "nguyen ngoc thach";
		
//		OutputStreamWriter oswrite = new OutputStreamWriter(new FileOutputStream(path),"UTF-16");
//		char[] text = {'n','g','o','c',' ','t','h','a','c','h'};
//		oswrite.write(text, 0, text.length);
//		oswrite.write(text1);
//		oswrite.close();
		
		
		InputStreamReader isread = new InputStreamReader(new FileInputStream(path),"UTF-8");
		char[] arr = new char[100];
		int rNumber = isread.read(arr, 0, arr.length);
		String t;
		for(int i =0; i < rNumber; i++) {
			t = isread.getEncoding();
			System.out.println(t);
		}
		isread.close();
		
//		PrintStream printStream = new PrintStream(new FileOutputStream(path));
//		printStream.print("nguyen");
//		printStream.println("ngoc");
//		printStream.close();
		
		
	}

}
