package lesson3.Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	public static void main(String[] args) throws IOException {
		byte[] buff = new byte[1024];
		DatagramSocket serverSocket = new DatagramSocket(2000);
		DatagramPacket packet = new DatagramPacket(buff, buff.length);
		
		String line;
		while(true) {
			//nhận packet từ client
			packet.setData(buff);
			packet.setLength(buff.length);
			serverSocket.receive(packet);
			line = new String(packet.getData(), 0, buff.length);//translate packet recieved (byte[]) to content

			if(line.equalsIgnoreCase("exit")) break;
			
			line = "Echo: " + line;
			byte[] data = line.getBytes();//translate data (string) to byte to send client
			//send from server to client
			packet.setData(data);
			packet.setLength(data.length);
			serverSocket.send(packet);
		}
		
		serverSocket.close();
	}

}
