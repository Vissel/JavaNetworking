package lesson1;

import java.io.File;
import java.io.IOException;

public class Exercise3 {
	// neu la thu muc thi ki hieu +
	// neu la file thi ki hieu -
	public static String save = "";

	public void dirTree(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("path not exists");
		}else if (file.isFile()) {
			System.out.println(save + "- " + file.getName());
		} else if (file.isDirectory()) {
			File[] list = file.listFiles();
			for (File l : list) {
				if (l.isFile()) {
					System.out.println(save + "- " + l.getName());
				} else if (l.isDirectory()) {
					System.out.println(save + "+ " + l.getName());
					save += "\t";
					dirTree(l.getCanonicalPath());
				}
			}

		} 
//		char[] temp = save.toCharArray(); 
//		for(int i = 0; i < temp.length; i++) {
//		System.out.print(temp[i]+" ");
//		}
//		System.out.println(save);
//		else if (file.isDirectory()) {
//			System.out.println(file);
//			File[] list = file.listFiles();
//			for (File f : list) {
//				dirTree(f.getCanonicalPath());
//			}
//		}
	}

	public static void main(String[] args) throws IOException {
		Exercise3 e3 = new Exercise3();
//		String path = "D:\\eclipse-workspace\\data-test - Copy\\word1.docx";
		String path = "D:\\eclipse-workspace\\data-test - Copy";
//		String path = "D:\\abc.txt";
//		String path = "D:\\eclipse";
//		System.out.println(e3.dirTree(path));
		e3.dirTree(path);
	}
}
