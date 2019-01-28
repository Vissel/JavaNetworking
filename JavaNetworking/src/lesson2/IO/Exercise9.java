package lesson2.IO;

import java.io.File;
import java.util.Stack;

public class Exercise9 {
	/*
	 * 9. Viết ứng dụng String fileType(String fname) xác định file bất kỳ có phải
	 * là: zip, rar, doc, … (Kiểu file)
	 */

	public static String fileType(String fname) {
		String result = "";
		File file = new File(fname);
		if (!file.exists())
			result = "file not exits";
		else if (file.isDirectory())
			result = fname + " is directory";
		else if (file.isFile()) {
			String name = file.getName();

			String temp = name.replace('.', '/');
			String[] consider = temp.split("/");

			Stack<String> stack = new Stack<String>();
			for (int i = 0; i < consider.length; i++) {
				stack.push(consider[i]);
			}
			result = stack.pop();
			switch (result) {
			case "docx":
				result = name + " is word file";
				break;
			case "xlsx":
				result = name + " is exel file";
				break;
			case "jar":
				result = name + " is jar file";
				break;
			case "war":
				result = name + " is war file";
				break;
			case "txt":
				result = name + " is text  file";
				break;
			default:
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String fname = "D:\\eclipse-workspace\\data-test - Copy\\word3.docx";
		System.out.println(fileType(fname));
	}

}
