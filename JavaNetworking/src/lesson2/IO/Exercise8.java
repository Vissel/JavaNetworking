package lesson2.IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise8 {

	/*
	 * 8. Viết CT Pack/Unpack lưu nguyên thư mục vào 1 file duy nhất, trích rút từng
	 * file cụ thể (tương tự zip nhưng không nén) (giai đoạn 1 giả sử thục mục không
	 * chức thu mục con và restore nguyên cả thư mục)
	 */

	public static void pack(String pathFolder, String pathFile) throws IOException {
		File file = new File(pathFolder);

		if (!file.exists())
			System.out.println(pathFolder + "not exists");
		else {
			long start = System.currentTimeMillis();
			BufferedInputStream input;
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(pathFile));

			int data;
			File[] listFile = file.listFiles();

			for (File f : listFile) {
				if (f.isFile()) {
					input = new BufferedInputStream(new FileInputStream(f));
					data = input.read();
					while (data != -1) {
						output.write(data);
						data = input.read();
					}
					input.close();
				}
			}

			output.close();
			long end = System.currentTimeMillis();

			System.out.println("time Archive: " + (end - start) + "ms");
		}

	}
	
	public static void unPack(String filePath, String dirPath) {
		File file = new File(filePath);
		
		
	}

	public static void main(String[] args) throws IOException {

		String pathFolder = "D:\\eclipse-workspace\\data-test - Copy";
		String pathFile = "D:\\eclipse-workspace\\data-test - Copy\\b.archive.jar";
		pack(pathFolder, pathFile);
	}

}
