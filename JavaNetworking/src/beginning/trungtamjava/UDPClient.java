package beginning.trungtamjava;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) throws IOException{
		System.out.println("UDPClient open");
		InetAddress inetAddress = InetAddress.getByName("localhost");
		
		DatagramSocket datagramSocket = new DatagramSocket(6666,inetAddress);
		
		byte[] buf = new byte[1024];
		
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
		
		datagramSocket.receive(datagramPacket);
		
		String str = new String(datagramPacket.getData(), 0, buf.length);
		
		System.out.println(str);
		
		datagramSocket.close();
		System.out.println("UDPClient close");
	}

}
