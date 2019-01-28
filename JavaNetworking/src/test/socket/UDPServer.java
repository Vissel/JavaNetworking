package test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	// UDP
	// write program communicate between server and client
	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[256];
		DatagramSocket socket = new DatagramSocket(7);
		DatagramPacket packet = new DatagramPacket(buf, buf.length);

		BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while (true) {
			// server recieved
			packet.setData(buf);
			packet.setLength(buf.length);
			socket.receive(packet);

			String data = new String(packet.getData(), 0, buf.length); // translate

			if (data.equals("exit"))
				break;
			System.out.println("Client said : " + data);

			// server send
			line = readIn.readLine();
			byte[] dataSend = line.getBytes();
			packet.setData(dataSend);
			packet.setLength(dataSend.length);
			socket.send(packet);
		}
		socket.close();
	}

}
