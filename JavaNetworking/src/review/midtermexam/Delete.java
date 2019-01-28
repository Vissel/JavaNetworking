package review.midtermexam;

import java.io.File;
import java.io.IOException;

public class Delete {
	//xoa tat ca nhung gi co the xoa
	public boolean deleteAll(String path) {
		File file = new File(path);
		if(!file.exists()) return false;
		if(file.isFile()) file.delete();
		if(file.isDirectory()) {
			File[] list = file.listFiles();
			for(File f : list) f.delete();
		}
		return true;
	}
	//xoa file, file trong thu muc)
	public boolean deleteFile(String path) throws IOException {
		File file = new File(path);
		if(!file.exists()) return false;
		if(file.isFile()) file.delete();
		if(file.isDirectory()) {
			File[] list = file.listFiles();
			for(File f : list) {
				deleteFile(f.getCanonicalPath());// goi de quy de xoa duong dan tuyet doi cua thu muc
				}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		String path = "D:\\eclipse-workspace\\data-test - Copy\\folder3";
		
		Delete e1= new Delete();
		System.out.println(e1.deleteAll(path));
	}

}
