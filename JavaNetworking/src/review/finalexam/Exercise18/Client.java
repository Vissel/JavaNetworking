package review.finalexam.Exercise18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	 Socket socket;
	 public DataInputStream netIn; //dung download
	 public DataOutputStream netOut;//dung upload
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
		String srcFile = "D:\\eclipse-workspace\\data-test - Copy\\ex15.txt";
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\ex16(upload).txt";
		
		client.connection();
//		client.upload(srcFile, desFile);
		client.download();
		client.close();
	}
	
	public void connection() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 7);
		netOut = new DataOutputStream(socket.getOutputStream());
	}
	public void close() throws IOException {
		netOut.close();
		socket.close();
	}
	private void download() throws IOException {
		netIn = new DataInputStream(socket.getInputStream());
		netOut.writeUTF("download"); //send command download
		
		long fileSize=netIn.readLong();
		String desFile = netIn.readUTF();
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
		int data;
		for(int i =0; i < fileSize ; i++) {
			data = netIn.read();
			bos.write(data);
		}
		bos.close();
		netIn.close();
	}

	public void upload(String srcFile, String desFile) throws IOException {
		File file = new File(srcFile);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		
		netOut.writeUTF("upload"); //send command upload
		netOut.flush();
		netOut.writeLong(file.length()); //send file size
		netOut.writeUTF(desFile); //send des name file
		
		byte[] byteRead = new byte[1024];
		int data;
		while((data = bis.read(byteRead)) !=-1) {
			netOut.write(byteRead, 0, data);
			netOut.flush();
		}
		bis.close();
		
		
	}

}
