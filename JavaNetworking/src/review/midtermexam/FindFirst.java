package review.midtermexam;

import java.io.File;
import java.io.IOException;

public class FindFirst {
	public boolean findFirst(String path, String pattern) throws IOException {
		File file = new File(path);
		if(!file.exists()) {return false;}
		else {
			File[] list = file.listFiles();
			String str;
			for(File f : list) {
				str = f.toString();
				if(str.contains(pattern)) {
					System.out.println(str);
				}
			}
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		String path ="D:\\eclipse-workspace\\data-test - Copy";
		File f = new File(path);
		File[] list = f.listFiles();
//		for(File file : list) {
//			System.out.println(file.getName());
//		}
//		System.out.println(f.toString());
//		System.out.println(f.getAbsolutePath());
//		System.out.println(f.getCanonicalPath());
//		System.out.println(f.getPath());
		
		FindFirst e2 = new FindFirst();
		System.out.println(e2.findFirst(path, ".docx"));
	}
}
