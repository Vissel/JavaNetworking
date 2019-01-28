package beginning.trungtamjava;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Client ket noi");
		Socket socket = new Socket("localhost", 6666);
		
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream()); //doc du lieu tu server
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());//goi du lieu den server
		
		Scanner scanner = new Scanner(System.in);
		
		
		while(true) {
			//goi du lieu den server
			String str1 = scanner.nextLine();
			dataOutputStream.writeUTF(str1);
			dataOutputStream.flush();
			if(str1.equals("quit")) break;
			
			//doc du lieu tu server
			String str2 = dataInputStream.readUTF();
			System.out.println("Server says: "+ str2);
			
		}
		
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
	}

}
