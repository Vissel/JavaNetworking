package review.finalexam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Exercise9 {
	/*
	 * Viết CT copy/move thư mục dùng byte array kết hợp với BIS, BOS: boolean
	 * folderCopy(String sFolder, String destFolder, boolean moved);
	 */
	public static void main(String[] args) throws IOException {
//		String name = "thach.doc.xlsx";
//		System.out.println(ext(name));
		String sFolder = "D:\\eclipse-workspace\\data-test - Copy\\folder4";
		String desFolder = "D:\\eclipse-workspace\\data-test - Copy\\folder6";
		Exercise9 e9 = new Exercise9();
		System.out.println(e9.folderCopy(sFolder, desFolder, true));
	}

	public boolean folderCopy(String sFolder, String desFolder, boolean moved) throws IOException {
		File file = new File(sFolder);
		if (!file.exists())
			return false;
		else {
			File folder = new File(desFolder);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			BufferedInputStream bis;
			BufferedOutputStream bos;
			File[] list = file.listFiles();
			for (File f : list) {
				if (f.isDirectory()) {
					this.folderCopy(f.getCanonicalPath(), desFolder + "\\" + f.getName(), moved);
				} else if (f.isFile()) {
					bis = new BufferedInputStream(new FileInputStream(f));
					bos = new BufferedOutputStream(new FileOutputStream(desFolder + "\\" + f.getName()));
					byte[] byteRead = new byte[1024];
					int data;
					while ((data = bis.read(byteRead)) != -1) {
						bos.write(byteRead, 0, data);
					}
					bis.close();
					bos.close();
					if (moved) {
						f.delete();
					}
				}
			}
			if(moved) file.delete();
			return true;
		}
	}

}
