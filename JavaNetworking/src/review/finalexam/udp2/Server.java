package review.finalexam.udp2;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
/*Viet chuong trinh copy file qua mang su dung giao thuc UDP*/
	public static void main(String[] args) throws IOException {
		byte[] buff= new byte[1024];
		DatagramSocket socket = new DatagramSocket(16);
		DatagramPacket packag = new DatagramPacket(buff, buff.length);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\eclipse-workspace\\folder4\\pack1(udp).zip"));
		
		packag.setData(buff);
		packag.setLength(buff.length);
		socket.receive(packag);
		Long sizeFile = new Long(packag.getLength());
		
		for(int i =0; i < sizeFile ; i ++) {
			packag.setData(buff);
			packag.setLength(buff.length);
			socket.receive(packag);
			bos.write(packag.getData(), 0, buff.length);
		}
		bos.close();
		socket.close();
	}

}
