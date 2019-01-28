package beginning.trungtamjava;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

	public static void main(String[] args) throws IOException {
		System.out.println("Server open");
		ServerSocket server = new ServerSocket(6666);
		
		Socket socket = server.accept();
		
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream()); //doc du lieu tu client
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());//goi du lieu den client
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			//doc du lieu tu client
			String str1 = dataInputStream.readUTF();
			if(str1.equals("quit")) break;
			else {
				System.out.println("Client says: "+ str1);
			}
			
			//truyen du lieu den client
			String str2 = scanner.nextLine();
			dataOutputStream.writeUTF(str2);
			dataOutputStream.flush();
		}
		
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
		server.close();
		System.out.println("Server close");
	}

}
