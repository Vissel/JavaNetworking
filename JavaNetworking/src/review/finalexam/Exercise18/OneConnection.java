package review.finalexam.Exercise18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class OneConnection extends Thread{
	Socket serverSocket;
	DataInputStream netIn ;// dung upload
	DataOutputStream netOut ; //dung download
	String command,
	srcFile="D:\\eclipse-workspace\\data-test - Copy\\ex15.txt",
	desFile="D:\\eclipse-workspace\\data-test - Copy\\ex15(download).txt";
	
	public OneConnection(Socket socket) throws IOException {
		this.serverSocket = socket;
		netIn = new DataInputStream(serverSocket.getInputStream());//doc dong du lieu qua socket
		netOut = new DataOutputStream(serverSocket.getOutputStream()) ; //send du lieu qua socket
	}
	
	@Override
	public void run() {
		try {
			command = netIn.readUTF();
			if(command.equalsIgnoreCase("upload")) {
				upload();
			}else if(command.equalsIgnoreCase("download")) {
				download(srcFile,desFile);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void download(String sFile,String desFile) throws IOException {
		File file = new File(sFile);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		netOut.writeLong(file.length()); // file size
		netOut.writeUTF(desFile);
		byte[] byteRead = new byte[1024];
		int data;
		while((data = bis.read(byteRead)) != -1) {
			netOut.write(byteRead, 0, data);
			netOut.flush();
		}
		bis.close();
	}
	private void upload() {
		try {
			
			long fileSize = netIn.readLong();
			String desFile = netIn.readUTF();
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
			
			int data;
			for(int i =0; i < fileSize; i++) {
				data = netIn.read();
				bos.write(data);
			}
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
