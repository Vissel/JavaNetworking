package lesson1;

import java.io.File;
import java.io.IOException;

public class Exercise1 {

	// delete 1 file or thu muc bat ky
	// kiểm tra xem là file hay thư mục
	// ktra tồn tại hay ko?

	//chua dung
	public boolean deleteTest(String path) {
		boolean result = false;
		File url = new File(path);
		if (url.exists()) {
			File[] list = url.listFiles();
			for (int i = 0; i < list.length; i++) {
				if (list[i].exists() && list[i].isFile()) {
					list[i].delete();
					result = true;
				} else {
					result = false;
				}
			}
		}
		return result;
	}

	// delete tất cả nhung gi có the xoa dc
	public boolean delete(String path) throws IOException {
		File url = new File(path);
		if (!url.exists())
			return true; // neu ko ton tai thi KQ da hoan thanh

		if (url.isDirectory()) {
			File[] list = url.listFiles();
			if (list != null)
				for (File f : list)
					delete(f.getCanonicalPath()); // xoa noi dung ben trong thu muc
		}
		return url.delete();// xoa thu muc
	}

	// xoa tat ca cac file chi chua lai thu muc
	public boolean deleteFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists())
			return false; // neu no ko ton tai thi tra ve false
		if (file.isFile()) {
			file.delete();
		} else if (file.isDirectory()) {
			File[] list = file.listFiles();
			for (File f : list)
				deleteFile(f.getCanonicalPath());
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
//		File path = new File("D:\\eclipse-workspace\\data-test");
//		if (path.exists()) {
//			File[] list = path.listFiles();
//			for (File l : list) {
//				System.out.println(l);
//			}
//		}

		Exercise1 e1 = new Exercise1();
		String path = "D:\\eclipse-workspace\\data-test - Copy";

		// test delete
//		System.out.println(e1.delete(path));

		// test deleteFile
		System.out.println(e1.deleteFile(path));
	}
}
