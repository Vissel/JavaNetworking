package lesson3.Socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Exercise18Server {
	ServerSocket server;
	Socket socket;
	DataInputStream netIn;
	DataOutputStream destination;
	
	public void connection(int port) throws IOException {
		server = new ServerSocket(port);
		socket = server.accept();
		
		netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	public void update() throws IOException {
		long size = netIn.readLong();
		String desPath = netIn.readUTF();
		
		destination = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(desPath)));
		int data;
		for(int i =0; i < size; i++) {// chừng nào chưa đọc hết dữ liệu
			data = netIn.read();
			destination.write(data);
			destination.flush();
		}
		
		destination.close();
	}
	public void close() throws IOException {
		netIn.close();
		socket.close();
		server.close();
	}
	public static void main(String[] args) throws IOException {
		int port = 7;
		
		Exercise18Server server = new Exercise18Server();
		server.connection(port);
		server.update();
		server.close();
	}

}
