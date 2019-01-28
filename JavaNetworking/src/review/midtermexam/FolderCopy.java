package review.midtermexam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

public class FolderCopy {
	/*
	 * Bài 6: Viết CT copy/move thư mục dùng byte array kết hợp với BIS, BOS:
	 * boolean folderCopy(String sFolder, String destFolder, boolean moved);
	 * 
	 */

	public boolean folderCopy(String sFile, String desFile, boolean moved) throws IOException {
		File file = new File(sFile);
		File dir = new File(desFile);
		dir.mkdirs();
		if (!file.exists()) {
			return false;
		} else {
			File[] list = file.listFiles();
			for (File f : list) {
				if (f.isDirectory()) {
					folderCopy(f.getCanonicalPath(), desFile + "\\" + f.getName(), moved);
				} else if (f.isFile()) {
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dir + "\\" + ext(f)));
					byte[] arr = new byte[1024];
					int data = bis.read(arr);
					while (data != -1) {
						bos.write(arr, 0, data);
						data = bis.read(arr);
					}
					bis.close();
					bos.close();
				}
			}
			// copy hay moved ?
			if (moved == false)
				return true;
			else {
				deleteAll(sFile);
				return true;
			}
		}
	}

	// delete path if choose move
	public void deleteAll(String path) throws IOException {
		File file = new File(path);
		if (!file.exists())
			System.out.println("path not exits!");
		else {
			File[] list = file.listFiles();
			for (File f : list) {
				if (f.isFile()) {
					f.delete();
				}
				else if (f.isDirectory())
					deleteAll(f.getCanonicalPath());
			}
			file.delete();
		}
	}

	// tao file (copy)
	public static String ext(File f) {
		String res = "";
		StringTokenizer token = new StringTokenizer(f.getName(), ".");
		String temp1, temp2;
		temp1 = token.nextToken();
		temp2 = token.nextToken();
		res = temp1 + "(copy)." + temp2;
		return res;
	}

	public static void main(String[] args) throws IOException {
		String sFile = "D:\\eclipse-workspace\\folder5";
		String desFile = "D:\\eclipse-workspace\\folder6";
		boolean moved = true;
		FolderCopy copy = new FolderCopy();
		System.out.println(copy.folderCopy(sFile, desFile, moved));
//		System.out.println(ext(new File(desFile)));
//		File file = new File(desFile);
//		System.out.println(file.mkdirs());
//		System.out.println(file.getPath());
//		System.out.println(file.delete());
	}

}
