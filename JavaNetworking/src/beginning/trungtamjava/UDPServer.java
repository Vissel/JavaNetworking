package beginning.trungtamjava;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

	public static void main(String[] args) throws IOException{
		System.out.println("UDPServer open");
		
		DatagramSocket datagramSocket = new DatagramSocket();
		
		InetAddress inetAddress = InetAddress.getByName("localhost");
		
		String str = "Xin chao UDPserver";
		
		DatagramPacket datagramPacket = new DatagramPacket(str.getBytes(), str.length(), inetAddress, 6666);
		
		datagramSocket.send(datagramPacket);
		
		datagramSocket.close();
		System.out.println("UDPServer close");
	}

}
