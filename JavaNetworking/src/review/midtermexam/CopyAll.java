package review.midtermexam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CopyAll {
	public void copyALL(String srcDir, String desDir, String ext1, String ext2) throws IOException {
		File file = new File(srcDir);
		BufferedInputStream fis;
		BufferedOutputStream fos;
		Stack<String> stack = new Stack<>();//stack de pop phan ext tim dc ra va xet dk
		List<File> saveFile = new ArrayList<>(); //list de luu nhung file tim dc co chua phan ext
		
		if(!file.exists()) System.out.println("Path is not exists!");
		else if(file.isDirectory()) {
			File[] list = file.listFiles();
			for(File l : list) {
				if(l.isDirectory()) copyALL(l.getCanonicalPath(), desDir, ext1, ext2);
				else {//neu la file
					String replace = l.getName().replace('.','/');
					String[] split = replace.split("/");
					for(String str : split) {
						stack.push(str);
					}
					String evalutorExt = stack.pop();
					if(ext1.contains(evalutorExt) || ext2.contains(evalutorExt)) { //so sanh voi ext, neu ok thi add vao list
						saveFile.add(l);
					}
				}
			}
		}
		
		//copy
		for(File f : saveFile) {
			fis = new BufferedInputStream( new FileInputStream(f));
			fos = new BufferedOutputStream( new FileOutputStream(desDir+"\\"+f.getName()));
			
//			byte[] readByte = new byte[1024];
			int data = fis.read();
			while((data )!= -1) {
				data = fis.read();
				fos.write(data);
			}
			fis.close();
			fos.close();
		}
	}
	public static void main(String[] args) throws IOException {
		String srcDir = "D:\\eclipse-workspace\\data-test - Copy";
		String desDir = "D:\\eclipse-workspace\\data-test - Copy\\folder2";
		String ext1 = ".txt"; String ext2 =".docx";
		CopyAll cp = new CopyAll();
		cp.copyALL(srcDir, desDir, ext1, ext2);
	}

}
