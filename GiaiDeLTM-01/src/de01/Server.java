package de01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(12345);
		while(true){
			Socket socket = server.accept();
			OneConnection connection = new OneConnection(socket);
			connection.start();
		}
	}

}
