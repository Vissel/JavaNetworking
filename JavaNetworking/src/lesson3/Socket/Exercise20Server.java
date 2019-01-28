package lesson3.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Exercise20Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(12345);
		while (true) {
			Socket socket = server.accept();
			Exercise20OneConnection oneConnection = new Exercise20OneConnection(socket);
//			Exercise20MrTinhConnection oneConnection = new Exercise20MrTinhConnection(socket);
			oneConnection.start();
		}
	}

}
