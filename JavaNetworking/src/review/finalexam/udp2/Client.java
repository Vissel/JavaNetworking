package review.finalexam.udp2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws IOException {
		byte[] data = new byte[1024];
		InetAddress address = InetAddress.getByName("localhost");
 		DatagramSocket socket = new DatagramSocket();
 		DatagramPacket packag = new DatagramPacket(new byte[100], 100, address, 16);
 		
 		File file = new File("D:\\eclipse-workspace\\data-test - Copy\\ex15.txt");
 		long sizeFile = file.length();
 		byte[] size = new byte[(int)sizeFile];
 		packag.setData(size);
 		packag.setLength(size.length);
 		socket.send(packag);
 		
 		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
 		int d;
 		while((d = bis.read(data)) != -1) {
 			packag.setData(data);
 			packag.setLength(data.length);
 			socket.send(packag);
 		}
 		bis.close();
 		socket.close();
	}

}
