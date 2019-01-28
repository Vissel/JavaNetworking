package review.finalexam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise14 {
/*14.	(5) Viết CT Pack/Unpack lưu nguyên thư mục vào 1 file duy nhất, trích rút từng file cụ thể (tương tự zip nhưng không nén)
 *  (giai đoạn 1 giả sử thục mục không chức thu mục con và restore nguyên cả thư mục)*/
	public static void main(String[] args) throws IOException {
		String srcFolder = "D:\\eclipse-workspace\\data-test - Copy\\folder3";
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\pack1.zip";
		pack(srcFolder, desFile);

//		File file = new File(srcFolder);
//		File[] list = file.listFiles();
//		System.out.println(list.length);
		
//		unpack(desFile,"D:\\eclipse-workspace\\data-test - Copy\\folder4");
	}

	//pack
	public static void pack(String srcFolder, String desFile) throws IOException {
		BufferedInputStream bis ;
		DataOutputStream bos = new DataOutputStream( new FileOutputStream(desFile));
		File folder = new File(srcFolder);
		
		if(!folder.exists()) System.out.println("path not exist");
		else if (folder.isFile()) System.out.println("path is file");
		else if (folder.isDirectory()) {
			File[] listFile = folder.listFiles();
			byte[] byteRead = new byte[1024];
			int data;
			bos.writeInt(listFile.length);//save size list
			for(File f : listFile) {
				bis = new BufferedInputStream(new FileInputStream(f));
				bos.writeUTF(f.getName());
				bos.writeLong(f.length());
				while((data = bis.read(byteRead)) != -1) {
					bos.write(byteRead, 0, data);
				}
				bis.close();
			}
		}
		bos.close();
	}
	
	//unpack
	public static void unpack(String srcFile, String desFolder) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(srcFile));
		int start = (int) System.currentTimeMillis();
		File folder = new File(desFolder);
		if(!folder.exists()) folder.mkdirs();
		BufferedOutputStream bos ;
		String name;
		long fileSize;
		
		int size = dis.readInt(); //read size
		for(int i = 0 ; i < size ; i++) {
			name = dis.readUTF(); //read name
			fileSize = dis.readLong(); //read file size
			bos= new BufferedOutputStream(new FileOutputStream(desFolder + "\\" +name));
			int data;
			for(int j = 0 ; j <fileSize ; j++) {
				data = dis.read();
				bos.write(data);
			}
			bos.close();
		}
		
		dis.close();
		int end =(int) System.currentTimeMillis();
		System.out.println(end - start);
	}
	
}
