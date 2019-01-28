package extention.UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		byte[] buf = new byte[1024];
		int length = buf.length;
		InetAddress address = InetAddress.getByName("127.0.0.1");
		int port = 2000;
		
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = new DatagramPacket(buf, length, address, port);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream object = new ObjectOutputStream(baos);
		
		Student st = new Student(1111, "Nguyễn Ngọc Thạch", 10.0);
		while(true) {
			object.writeObject(st);
			object.flush();
			byte[] data = baos.toByteArray();
			
			packet.setData(data);
			packet.setLength(data.length);
			socket.send(packet);
			
			object.close();
		}
	}

}
