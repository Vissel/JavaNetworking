package lesson2.IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class Exercise0 {

	public static void main(String[] args) throws IOException {
//		int port =1 ;
//		while(port <= 65355) {
//			try {
//				ServerSocket server = new ServerSocket(port);
//			} catch (IOException e) {
//				e.printStackTrace();
//				System.out.println("Port: " + port + "is openning");
//			}
//			port++;
//		}
		String srcFile = "D:\\eclipse-workspace\\GiaiDeLTM-01\\source\\1";
//		FileInputStream f = new FileInputStream(srcFile);
		File file = new File(srcFile);
		System.out.println(file.length());
//		System.out.println(f.available());
	}

}
