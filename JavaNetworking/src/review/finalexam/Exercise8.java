package review.finalexam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise8 {
/*Viết CT copy/move file dùng byte array kết hợp với BIS, BOS: 
boolean fileCopy(String sFile, String destFile, boolean moved);
 */
	public static boolean fileCopy(String sFile, String desFile, boolean moved) throws IOException {
		File file = new File(sFile);
		if(!file.exists() || file.isDirectory()) return false;
		else {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
			int data ;
			byte[] byteRead = new byte[100*1024];
			while((data = bis.read(byteRead))!= -1) {
				bos.write(byteRead, 0, data);
			}
			bis.close();
			bos.close();
			if(moved) {
				file.delete();
			}
			return true;
		
		}
	}
	public static void main(String[] args) throws IOException {
		String sFile = "D:\\eclipse-workspace\\data-test - Copy\\folder2\\apache-tomcat-8.5.35(Copy).zip";
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip";
		System.out.println(fileCopy(sFile, desFile, false));
	}
}
