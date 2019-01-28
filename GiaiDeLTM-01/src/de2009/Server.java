package de2009;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(12345);
		while(true){
			Socket socket = server.accept();
			OneConnection connect = new OneConnection(socket);
			connect.start();
		}
	}

}
