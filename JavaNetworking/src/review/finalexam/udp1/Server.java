package review.finalexam.udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
/*Viet chuong trinh trao doi thong tin giua client/server qua mang, su dung UDP*/
	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[1024];
		DatagramSocket socket = new DatagramSocket(15);
		DatagramPacket packag = new DatagramPacket(buf, buf.length);
		String line ;
		byte[] data;
		while(true) {
			//recieve from client
			packag.setData(buf);
			packag.setLength(buf.length);
			socket.receive(packag);
			line = new String(packag.getData(), 0, buf.length);
			if(line.equalsIgnoreCase("exit")) break;
			
			//send to client
			line = "Server say: " + line;
			data = line.getBytes();
			packag.setData(data);
			packag.setLength(data.length);
			socket.send(packag);
		}
		socket.close();
	}

}
