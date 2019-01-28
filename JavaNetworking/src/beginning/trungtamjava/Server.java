package beginning.trungtamjava;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			System.out.println("Server open");
			
			ServerSocket server = new ServerSocket(6666);
			Socket socket = server.accept();
			
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			
			String str = dataInputStream.readUTF();
			System.out.println(str);
			
			dataInputStream.close();
			
			socket.close();
			server.close();
			System.out.println("Server close");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
