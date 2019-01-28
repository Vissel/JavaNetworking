package lesson2.IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Test0 {

	public static void main(String[] args) throws IOException {
		
		String path = "D:\\eclipse-workspace\\data-test - Copy\\D1.txt";
		String a = "ABC";
		String b = "Thach";
		List<String> list = new ArrayList<>();
		list.add(a);
		list.add(b);

//		DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
		
//		dos.close();
		
//		RandomAccessFile raf = new RandomAccessFile(path, "rw");
//		System.out.println(raf.length());
//		raf.seek(1);
//		
//		raf.close();
		
//		DataInputStream dis = new DataInputStream(new FileInputStream(path));
//		System.out.println(dis.readUTF());
//		dis.close();
	}

}
