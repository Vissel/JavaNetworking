package lesson2.IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercise7 {
	// split with length
	public static String ext(int index) {
		String res = "";
		res = "" + index;
		while (res.length() < 3) {
			res = "0" + res;
		}
		return res;
	}
	
	// thay sua
	public static List<Object> splits(String fileName, int partSize) throws IOException {
		List<Object> result = new ArrayList<Object>();

		File file = new File(fileName);

		// tinh do luong de xem con phan du hay ko ?
		int count;
		boolean remain;
		count = (int) (file.length() / partSize);
		remain = (file.length() % partSize != 0);

		// mo file
		FileInputStream inputFile = new FileInputStream(file);
		FileOutputStream outputFile;
		int data;
		// vong lap for duyet qua cac phan cua file
		for (int index = 1; index <= count; index++) {
			outputFile = new FileOutputStream(file + "." + ext(index));
			for (int i = 0; i < partSize; i++) {// duyet tu i =0 -> partsize
				data = inputFile.read();// doc va ghi du lieu
				outputFile.write(data);
			}
			outputFile.close();
			result.add(outputFile);
		}
		// neu remain thi :.. chung nao con du lieu thi ... ghi vao file cuoi cung
		if (remain) {
			outputFile = new FileOutputStream(file + "." + ext(count + 1));
			while ((data = inputFile.read()) != -1) {
				outputFile.write(data);
			}
			outputFile.close();
			result.add(outputFile);
		}
		// close

		inputFile.close();

		return result;
	}

	/*-----join-----*/
	public static void joins(String fileName, int partSize, String pathDes) throws IOException {
		List<Object> list = splits(fileName, partSize);
		
		FileOutputStream desFile = new FileOutputStream(pathDes);
		FileInputStream srcFile;
		
		
		
		
		desFile.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName = "D:\\eclipse-workspace\\data-test - Copy\\folder3\\apache-tomcat-8.5.35.zip";
		int partSize = 3072;
		splits(fileName, partSize);
		
	}

}
