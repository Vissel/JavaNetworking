package review.midtermexam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Test {

	public static void main(String[] args) throws IOException {
		String path = "D:\\eclipse-workspace\\folder4\\test1.txt";
//		
		File file = new File(path);
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
//		raf.writeLong(100);
//		raf.writeLong(200);
//		raf.writeLong(300);
//		raf.writeLong(400);
//		raf.writeLong(500);
		raf.seek(16);
		raf.writeInt(600);
		System.out.println(raf.length());
//		System.out.println(raf.getFilePointer());
		raf.close();
	}

}
