package lesson3.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.*;

public class Echo {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7);
		while (true) {
			Socket socket = server.accept();
			
			OneConnection oneConnect = new OneConnection(socket);
			oneConnect.start();
		}
	}
}
