package lesson1;

import java.io.File;
import java.io.IOException;

public class Exercise0 {
	
	public static boolean check(String path) {
		File file = new File(path);
		if(file != null) return true;
		else return false;
	}
public static void main(String[] args) throws IOException {
	
	File url = new File("D:\\eclipse-workspace\\data-test - Copy\\folder2");
	File[] list = url.listFiles();
	
	for(File l : list) {
		System.out.println(l.getName());
//	System.out.println(l.getCanonicalPath());
	}
//	System.out.println(url.getCanonicalFile());
//	System.out.println(url.getPath());
//	System.out.println(url.isAbsolute());
//	System.out.println(url.isHidden());
//	System.out.println(url.mkdir());
	
//	File url1 = new File("D:\\eclipse-workspace\\data-test - Copy\\folder2");
//	System.out.println(url1.mkdirs());
	
//	System.out.println(url.delete());
	
	String path = "D:\\abc.txt";
//	System.out.println(check(path));
	
	File file = new File(path);
//	System.out.println(file.getName());
//	System.out.println(file.getPath());
//	System.out.println(file.getAbsolutePath());
	
}
}
