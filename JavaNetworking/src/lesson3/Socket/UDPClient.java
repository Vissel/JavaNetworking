	package lesson3.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[1024];
		int serverPort = 2000;
		InetAddress address = InetAddress.getByName("localhost");
		
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(new byte[1], 1, address, serverPort);
		BufferedReader netIn = new BufferedReader(new InputStreamReader(System.in));//doc du lieu tu nguoi dung
		
		String line;
		while(true) {
			line = netIn.readLine();
			byte[] data = line.getBytes(); //translate content to byte
			
			//send from client to server
			packet.setData(data);
			packet.setLength(data.length);
			socket.send(packet);
			
			if(line.equalsIgnoreCase("exit")) break;
			
			//receive from server
			packet.setData(buf);
			packet.setLength(buf.length);
			socket.receive(packet);
			
			line = new String(packet.getData(), 0, buf.length);//translate packet received (byte[]) to content
			System.out.println(line);
		}
		socket.close();
	}

}
