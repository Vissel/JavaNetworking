package test.socket;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSetTimeout {
	//test time out before accept
	//This class is server
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7);
		server.setSoTimeout(30000); // set time out = 30s
		try {
		Socket socket = server.accept();
		System.out.println("connect success!");
		socket.close();
		}catch(InterruptedIOException e) {
			System.out.println("no connection within 30 second");
		}finally {
		server.close();
	}
	}

}
