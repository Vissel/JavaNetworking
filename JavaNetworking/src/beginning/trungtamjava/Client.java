package beginning.trungtamjava;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {

		try {
			System.out.println("Client ket noi toi server");
			Socket socket = new Socket("localhost", 6666);
			
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
			dataOutputStream.writeUTF("Hello Java networking");
			dataOutputStream.flush();
			
			dataOutputStream.close();
			
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
