package lesson2.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Exercise5 {

	public static boolean copyFile(String sourceFile,String desFile) throws IOException {
		FileInputStream source = new FileInputStream(sourceFile);
		FileOutputStream destination = new FileOutputStream(desFile);
		
		int timeStart = (int) System.currentTimeMillis();
		
		int data = source.read() ;
		while(data != -1) {
			data = source.read();
			destination.write(data);
			
		}
		int timeEnd = (int) System.currentTimeMillis();
		
		source.close();
		destination.close();
		
		System.out.println("time= "+ (timeEnd - timeStart));
		return true;
	}
	
	//cach khac
	public static boolean copyFiles(String sourceFile, String desFile) throws IOException {
		
		FileInputStream inputFile = new FileInputStream(sourceFile);
		FileOutputStream outputFile = new FileOutputStream(desFile);
		
		int timeStart = (int) System.currentTimeMillis();
		
		byte[] dataSize = new byte[1024];
		int data ;
		while((data = inputFile.read(dataSize))!= -1) {
			outputFile.write(dataSize,0,data);
//			data = inputFile.read(dataSize);
		}
		 
		int timeEnd = (int) System.currentTimeMillis();
		
		inputFile.close();
		outputFile.close();
		
		System.out.println("time= "+ (timeEnd - timeStart));
		return true;
	}
	public static void main(String[] args) throws IOException {
		String sourceFile = "D:\\eclipse-workspace\\data-test - Copy\\word1.docx";
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\word1(copy).docx";
		System.out.println(copyFiles(sourceFile, desFile));
	}

}
