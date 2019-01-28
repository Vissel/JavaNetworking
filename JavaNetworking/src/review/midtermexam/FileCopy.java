package review.midtermexam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	/*
	 * Bài 5: Viết CT copy/move file dùng byte array kết hợp với BIS, BOS: boolean
	 * fileCopy(String sFile, String destFile, boolean moved);
	 * 
	 */

	// copy + moved
	public boolean fileCopy(String sFile, String desFile, boolean moved) throws IOException {
		File file = new File(sFile);
		if (!file.exists() || file.isDirectory())
			return false;
		else {
			FileInputStream fis = new FileInputStream(sFile);
			FileOutputStream fos = new FileOutputStream(desFile);

			byte[] byteArr = new byte[1024];
			int data = fis.read(byteArr);

			while (data != -1) {
				fos.write(byteArr, 0, data);
				data = fis.read(byteArr);
			}
			fis.close();
			fos.close();
			if (moved == false) {
				return true;// neu moved = false thi copy
			} else if (moved == true) { // neu moved == true thi moved
				file.delete();
			}
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		String sFile = "D:\\eclipse-workspace\\data-test - Copy\\exercise.docx";
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder1\\exercise(copy).docx";
		boolean moved = false;
		FileCopy copy = new FileCopy();
		System.out.println(copy.fileCopy(sFile, desFile, moved));
	}

}
