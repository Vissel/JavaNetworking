package review.finalexam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercise10join {
	/*
	 * Viết CT File Joiner ghép các file thành phần thành file ban đầu
	 */
	public static void main(String[] args) throws IOException {
		List<File> listFile = new ArrayList<>();
		listFile.add(new File("D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip001"));
		listFile.add(new File("D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip002"));
		listFile.add(new File("D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip003"));
		listFile.add(new File("D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip004"));
		listFile.add(new File("D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip005"));
		listFile.add(new File("D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip006"));
		
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache.zip";
		joinWithCapacity(listFile, desFile);
	}
	public static void joinWithCapacity(List<File> listFile, String desFile) throws IOException {
		BufferedInputStream bis ;
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
		
		byte[] byteRead = new byte[1024];
		for(int i = 0 ; i < listFile.size(); i ++) {
			bis = new BufferedInputStream(new FileInputStream(listFile.get(i)));
			int data ;
			while((data = bis.read(byteRead)) != -1) {
				bos.write(byteRead, 0, data);
			}
			bis.close();
		}
		bos.close();
	}
	
	
}
