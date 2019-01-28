package test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[256];
		InetAddress serverAddress = InetAddress.getByName("localhost");
		int portServer = 7;

		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(new byte[1], 1, serverAddress, portServer);
		BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while (true) {
			// client send
			line = readIn.readLine();

			if (line.equals("exit"))
				break;
			byte[] dataSend = line.getBytes();
			packet.setData(dataSend);
			packet.setLength(dataSend.length);
			socket.send(packet);

			// client recieved
			packet.setData(buf);
			packet.setLength(buf.length);
			socket.receive(packet);
			String strData = new String(packet.getData(), 0, buf.length);
			System.out.println("Server said : " + strData);
		}
		socket.close();
	}

}
