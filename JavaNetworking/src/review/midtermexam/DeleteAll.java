package review.midtermexam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DeleteAll {
	public void deleteAll(String path, String ext1, String ext2) throws IOException {
		Stack<String> stack = new Stack<String>(); // stack de luu cac fileName
		File file = new File(path);
		if (!file.exists())
			System.out.println(path + " is not exist!");
		else if (file.isDirectory()) {
			File[] listFile = file.listFiles();
			String[] arr;
			String tmp;
			for (File f : listFile) {
				if (f.isDirectory())//neu la thu muc thi goi đệ quy tìm trong thư mục đó
					deleteAll(f.getCanonicalPath(), ext1, ext2);
				else {//nếu là file thì tìm thôi
					tmp = f.getName().replace('.', '/');
					arr = tmp.split("/");
					for (String str : arr) {
						stack.push(str);
					}
					String popStr = stack.pop();
					if (ext1.contains(popStr) || ext2.contains(popStr))
						f.delete();
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		String path = "D:\\eclipse-workspace\\data-test - Copy";
		DeleteAll e4 = new DeleteAll();
		e4.deleteAll(path, ".docx", ".txt");
	}

}
