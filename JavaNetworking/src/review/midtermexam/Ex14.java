package review.midtermexam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ex14 { /* bài này làm chưa ra đáp án T T */
	/*
	 * 14. (5) Viết CT Pack/Unpack lưu nguyên thư mục vào 1 file duy nhất, trích rút
	 * từng file cụ thể (tương tự zip nhưng không nén) (giai đoạn 1 giả sử thục mục
	 * không chức thu mục con và restore nguyên cả thư mục)
	 */

	// pack
	public boolean pack(String folderPath, String desFile) throws IOException {
		File file = new File(folderPath);

		if (!file.exists() || file.isFile()) {
			System.out.println("Path isn't valid");
			return false;
		} else if (file.delete() == true) {
			System.out.println("path is empty");
			return false;
		} else {
			RandomAccessFile raf = new RandomAccessFile(desFile, "rw");
			FileInputStream fis;
			File[] listFile = file.listFiles();

			raf.writeInt(listFile.length);
			for (File f : listFile)
				raf.writeLong(0); // đánh dấu vị trí của các file sẽ lưu

			long current;
			for (int index = 0; index < listFile.length; index++) {
				current = raf.getFilePointer(); // lấy vị trí con trỏ hiện tại
				fis = new FileInputStream(listFile[index]);
				long nextEntiry = (index * 8) + 8;
				raf.writeLong(current);
				
				raf.seek(current);// di chuyển tới nơi để lưu dữ liệu, sau đó bắt đầu ghi dữa liệu
				if (index == listFile.length-1) {
					raf.writeLong(0);
				}else {
					raf.writeLong(nextEntiry);}
				raf.writeLong(listFile[index].length());
				raf.writeUTF(listFile[index].getName());
				byte[] byteSize = new byte[(int) listFile[index].length()];
				int data = fis.read(byteSize);
//				while (data != -1) {
				raf.write(byteSize,0,data);
//				data = fis.read(byteSize);
//				}
				fis.close();
			}
			raf.close();
			return true;
		}

	}

	public boolean unPack(String srcFile, String fileName, String desPath) throws IOException {
		File file = new File(srcFile);
		if (!file.exists()) {
			System.out.println("File isn't exits");
			return false;
		} else {
			File desFile = new File(desPath);
			if (!desFile.exists())
				desFile.mkdirs();
			RandomAccessFile raf = new RandomAccessFile(srcFile, "rw");
			FileOutputStream fos ;
			
			int size = raf.readInt(); // doc size
			for(int index = 0 ; index < size; index++) {
				long locate = raf.readLong(); // đọc vị trí 
				raf.seek(locate);//di chuyển đến vị trí chứa ND file
				raf.readLong(); //đọc nextEntity
				long length= raf.readLong(); // đọc length
				String name = raf.readUTF(); //đọc name
				if(fileName.contains(name)) {
					fos = new FileOutputStream(desPath + "\\" + fileName);
					byte[] byteSize = new byte[(int)length];
					int data = raf.read(byteSize);
					fos.write(byteSize, 0, data);
					fos.close();
				}
			}
			raf.close();
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		Ex14 packUnPack = new Ex14();
		String folderPath = "D:\\eclipse-workspace\\folder4";
		String desFile = "D:\\eclipse-workspace\\folder4\\a.pack";
		System.out.println(packUnPack.pack(folderPath, desFile));
//		System.out.println(packUnPack.unPack(desFile, "word1.docx", folderPath));
	}

}
