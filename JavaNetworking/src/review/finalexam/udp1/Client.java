package review.finalexam.udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		byte[] buff = new byte[1024];
		InetAddress address = InetAddress.getByName("localhost");
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packag = new DatagramPacket(buff, buff.length, address, 15);
		String line ;
		byte[] data;
		Scanner sc = new Scanner(System.in);
		while(true) {
			//send data to server
			line = sc.nextLine();
			data = line.getBytes();
			packag.setData(data);
			packag.setLength(data.length);
			socket.send(packag);
			if(line.equalsIgnoreCase("exit")) break;
			
			//recieve data from server
			packag.setData(buff);
			packag.setLength(buff.length);
			socket.receive(packag);
			line = new String(packag.getData(), 0, buff.length);
			System.out.println(line);
		}
	}

}
