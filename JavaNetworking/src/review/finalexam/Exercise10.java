package review.finalexam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise10 {
	/*
	 * Viết CT File Spliter chia 1 file thành nhiều phần theo dung lượng hoặc số
	 * lượng. Viết CT File Joiner ghép các file thành phần thành file ban đầu
	 */

	public static void main(String[] args) throws IOException {
//		System.out.println(ext(1));
		String sFile = "D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip";
		int capacity = 8192*100;
//		splitWithCapacity(sFile, capacity);
		splitWithQuantity(sFile, 6);
	}

	// split with capacity
	public static void splitWithCapacity(String sFile, int capacity) throws IOException {
		File file = new File(sFile);
		if (!file.exists())
			System.out.println("File not found");
		else {
			int count = (int) file.length() / capacity; // so luong file se slipt
			int remain = (int) file.length() % capacity; // so luong file con thua.
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos;

			int data;
			for(int i = 1; i <= count ; i++) {
				bos = new BufferedOutputStream( new FileOutputStream(file.getPath() + ext(i)));
				for(int j = 0; j < capacity; j ++) {
					data = bis.read();
					bos.write(data);
				}
				bos.close();
			}
			if(remain != 0) {
				bos = new BufferedOutputStream( new FileOutputStream(file.getPath() + ext(count +1)));
				while((data = bis.read()) != -1) {
					bos.write(data);
				}
				bos.close();
			}
			
			bis.close();
		}
	}

	private static String ext(int index) {
		String result = "";
		result = "" + index;
		while (result.length() < 3) {
			result = "0" + result;
		}
		return result;
	}

	// split with quantity
	public static void splitWithQuantity(String srcFile, int quantity) throws IOException {
		File file = new File(srcFile);
		if(!file.exists()) {
			System.out.println("file not exits");
		}else {
			int countSize = (int) file.length() / quantity; //tim dung luong
			boolean remain = (file.length() % quantity) != 0; //neu con du
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos;
			
			int data;
			for(int i =1 ; i <= quantity; i++) {
				bos = new BufferedOutputStream(new FileOutputStream(file.getPath() + ext(i)));
				for(int j = 0; j < countSize; j++) {
					data = bis.read();
					bos.write(data);
				}
				if(i == quantity && remain) {
					data = bis.read();
					bos.write(data);
				}
				bos.close();
			}
			bis.close();
		}
	}
}
