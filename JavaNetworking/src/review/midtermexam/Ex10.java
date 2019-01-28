package review.midtermexam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Ex10 {
	/*
	 * 10. (2)Viết CT File Spliter chia 1 file thành nhiều phần theo dung lượng hoặc
	 * số lượng. Viết CT File Joiner ghép các file thành phần thành file ban đầu.
	 */

	// chia theo so luong number da cho
	public void slipt(String srcFile, int number) throws IOException {
		File file = new File(srcFile);
		if (!file.exists() || file.isDirectory())
			System.out.println("Path isn't exits or isn't file");
		else {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos;
			int division = (int) (file.length() % number);
			int result = (int) (file.length() / number);
			for (int i = 0; i < fis.available(); i++) {
				for (int index = 1; index <= number; index++) {
					fos = new FileOutputStream(srcFile + "." + ext(file, index));
					byte[] size = new byte[result];
					int data = fis.read(size);
					fos.write(size, 0, data);

					if (division != 0) {
						if (index == number) {// truong hop number cuoi cung nhung van chua cat du du lieu
							byte[] remain = new byte[result + division];
							int data1 = fis.read(remain);
							fos.write(remain, 0, data1);
						}
						fos.close();
					} else {
						fos.close();
					}

				}
			}
			fis.close();

		}
	}

	// xac dinh dua tren nhung file nhap vao
	public void join(List<String> listFile, String desFile) throws IOException {
		FileInputStream fis;
		FileOutputStream fos = new FileOutputStream(desFile);

		for (String path : listFile) {
			fis = new FileInputStream(path);
			int data = fis.read();
			while (data != -1) {
				fos.write(data);
				data = fis.read();
			}
			fis.close();
		}
		fos.close();
	}

	public String ext(File file, int index) {
		String result = "";
		StringTokenizer token = new StringTokenizer(file.getName(), ".");
		String temp1, temp2;
		temp1 = token.nextToken();
		temp2 = token.nextToken();
//		System.out.println(temp1 + temp2);
		result = temp1 + index + "." + temp2;
		return result;
	}

	public static void main(String[] args) throws IOException {
		Ex10 split = new Ex10();
		String srcFile = "D:\\eclipse-workspace\\data-test - Copy\\word3.docx";
		split.slipt(srcFile, 6);

//		Ex10 join = new Ex10();
//		String path1 = "D:\\eclipse-workspace\\data-test - Copy\\word3.docx.word31.docx";
//		String path2 = "D:\\eclipse-workspace\\data-test - Copy\\word3.docx.word32.docx";
//		String path3 = "D:\\eclipse-workspace\\data-test - Copy\\word3.docx.word33.docx";
//		String path4 = "D:\\eclipse-workspace\\data-test - Copy\\word3.docx.word34.docx";
//		String path5 = "D:\\eclipse-workspace\\data-test - Copy\\word3.docx.word35.docx";
//		String path6 = "D:\\eclipse-workspace\\data-test - Copy\\word3.docx.word36.docx";
//		List<String> listFile = new ArrayList<>();
//		listFile.add(path1);
//		listFile.add(path2);
//		listFile.add(path3);
//		listFile.add(path4);
//		listFile.add(path5);
//		listFile.add(path6);
//		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder1\\word3(join).docx";
//		join.join(listFile, desFile);

	}

}
