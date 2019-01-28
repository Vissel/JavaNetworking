package review.finalexam.Exercise18;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
/*18.	(7)Viết CT client/server upload/download file lên server.
Người dùng tương tác với clietn qua console với cú pháp lệnh: copy source_file dest_file*/
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7);
		while(true) {
			Socket socket = server.accept();
			OneConnection connection = new OneConnection(socket);
			connection.start();
		}
		

		
	}
	
}
