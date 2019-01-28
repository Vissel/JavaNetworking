package lesson1;

import java.io.File;
import java.io.IOException;

public class Exercise2 {

	//
	public boolean findFirst(String path, String pattern) throws IOException {
		File file = new File(path);
		if(!file.exists()) {
			return false;
		}else {
			String[] content = file.list();
			for(String f : content) {
				if(f.contains(pattern))
				System.out.println(file + "\\" +f);// chơi ăn gian
			}
			return true;
		}
	}
	
	public boolean findSecond(String path, String pattern) throws IOException {
		File file = new File(path);
		if(!file.exists()) {
			return false;
		}else {
			File[] content = file.listFiles();
			for(File f : content) {
				String str = f.toString();
				if(str.contains(pattern)) System.out.println(str);
			}
			return true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Exercise2 e2 = new Exercise2();
		String path = "D:\\eclipse-workspace\\data-test - Copy";
		
		File f = new File(path);
		
		System.out.println(e2.findFirst(path, ".docx"));
		System.out.println(e2.findSecond(path, ".xlsx"));
		
	}
}
