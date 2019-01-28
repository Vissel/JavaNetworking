package de201805.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(55555);
		while(true){
			Socket socket = server.accept();
			OneConnection connect = new OneConnection(socket);
			connect.start();
		}
	}

}
