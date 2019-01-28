package Socket.ex22DB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7);
		while(true) {
			Socket socket = server.accept();
			OneConnection connect = new OneConnection(socket);
			connect.start();
		}
	}

}
