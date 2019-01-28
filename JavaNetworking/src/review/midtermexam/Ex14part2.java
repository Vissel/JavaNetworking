package review.midtermexam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
/* bai nay lam ra đáp án r nè */
public class Ex14part2 {
	public static boolean pack(String srcFolder, String desFile) throws IOException {
		File file = new File(srcFolder);

		if (!file.exists() || file.isFile()) {
			System.out.println("Path isn't valid");
			return false;
		} else if (file.delete() == true) {
			System.out.println("path is empty");
			return false;
		} else {
			BufferedInputStream bis;
			RandomAccessFile raf = new RandomAccessFile(desFile, "rw");

			File[] listFile = file.listFiles();
			long current, nextEtity;
			for (int index = 0; index < listFile.length; index++) {
				bis = new BufferedInputStream(new FileInputStream(listFile[index]));
				current = raf.getFilePointer();
				raf.writeLong(0);// lưu ví trí đầu tiên của file tiep theo
				raf.writeLong(listFile[index].length()); // lưu length
				raf.writeUTF(listFile[index].getName()); // lưu name
				// luu data
				byte[] byteSize = new byte[(int) listFile[index].length()];
				int data = bis.read(byteSize);
				raf.write(byteSize, 0, data);
				bis.close();

				if (index == listFile.length - 1) { // truong hop file cuoi cung, set lai Entity = 0
					raf.close();
				} else {
					// set lai gia tri next Entity
					nextEtity = raf.getFilePointer();
					raf.seek(current);
					raf.writeLong(nextEtity);
					raf.seek(nextEtity);
				}
			}
			return true;
		}
	}

	public static boolean unPack(String srcFile, String fileName, String desPath) throws IOException {
		File file = new File(srcFile);
		if (!file.exists()) {
			System.out.println("File isn't exits");
			return false;
		} else {
			File desFile = new File(desPath);
			if (!desFile.exists())// chua có thì mh tạo folder luôn
				desFile.mkdirs();
			RandomAccessFile raf = new RandomAccessFile(srcFile, "rw");
			BufferedOutputStream bos;

			long nextEntity;
			while (true) {
				nextEntity = raf.readLong(); // đọc nextEntity
				long length = raf.readLong(); // đọc length
				String name = raf.readUTF(); // đọc name
				if (fileName.equals(name)) { // dung name thi ghi du lieu vao file des
					bos = new BufferedOutputStream(new FileOutputStream(desPath + "\\" + name));
					byte[] byteSize = new byte[(int) length];
					int data = raf.read(byteSize);
					bos.write(byteSize, 0, data);
					bos.close();
					raf.close();
					break;
					
				} else {
					raf.seek(nextEntity);
				}
			}
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		String folderPath = "D:\\eclipse-workspace\\folder5";
		String desFile = "D:\\eclipse-workspace\\folder4\\b.pack";
//		System.out.println(pack(folderPath, desFile));
		System.out.println(unPack(desFile, "word3(copy)(copy)(copy)(copy).docx", folderPath));

	}

}
