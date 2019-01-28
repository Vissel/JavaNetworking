package extention.IO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.imageio.metadata.IIOInvalidTreeException;

public class Test0 {

	public static void main(String[] args) throws IOException {
		String path = "D:\\eclipse-workspace\\data-test - Copy\\word1.txt";
//		RandomAccessFile output = new RandomAccessFile(path, "rw");
//		
//		output.writeUTF("hello");
//		output.writeUTF("welcome");
//		output.writeUTF("to");
//		output.writeUTF("Rest Hotel");
//		System.out.println(output.getFilePointer());
//		System.out.println(output.length());
//		output.close();
		
		RandomAccessFile input = new RandomAccessFile(path, "rw");
		System.out.println(input.length());
////		input.seek();
//		System.out.println(input.getFilePointer());
////		input.writeUTF("!");
////		System.out.println(input.length());
//		
		input.close();
		
	}

}
