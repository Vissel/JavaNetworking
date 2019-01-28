package lesson3.Socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Exercise18Client {
/*
 *  -----------des---------
 * open source ----------size------------ create des
 * 
 * 
 * chung nao con du lieu ---------------- chung nao chua doc het du lieu
 * 
 * chưa giải quyết dc  vấn đề update 2 file, chưa download
 */
	Socket socket;
	DataOutputStream netOut;
	DataInputStream source;
	
	//update
	public void connection(String host,int port) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	public void update(String srcPath, String desPath) throws IOException {
		File file = new File(srcPath);
		long sizeSrcFile = file.length();
		
		source = new DataInputStream(new BufferedInputStream(new FileInputStream(srcPath)));
		//send size , address destination to server
		netOut.writeLong(sizeSrcFile);
		netOut.writeUTF(desPath);
		netOut.flush();
		
		//communication
		int data;
		while((data = source.read()) != -1) { //chừng nào còn dữ liệu
			data = source.read();
			netOut.write(data);
			netOut.flush();
		}
		source.close();
		
	}
	public void close() throws IOException {
		netOut.close();
		socket.close();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		String srcPath = "D:\\eclipse-workspace\\data-test - Copy\\file.rar";
		String desPath="D:\\eclipse-workspace\\data-test - Copy\\file(copy).rar";
		String host = "127.0.0.1";
		int port = 7;
		String s2= "D:\\eclipse-workspace\\data-test - Copy\\jojl.7z";
		String d2 = "D:\\eclipse-workspace\\data-test - Copy\\jojl(copy).7z";
		
		Exercise18Client client = new Exercise18Client();
		client.connection(host, port);
		client.update(srcPath, desPath);
		client.close();
		
	}

}
