package beginning.greekforgreek;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {

	private Socket socket = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	
	public SocketClient(String address, int port) {
		try {
			socket = new Socket(address, port);
			System.out.println("Client connect...");
			
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			Scanner scanner = new Scanner(System.in);
			String strWrite = "";
			
			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String strRead ="";
			
			while(!strWrite.equals("over")) {
				//sent message to server
				strWrite = scanner.nextLine();
				dos.writeUTF(strWrite);
				dos.flush();
				
				//received message from server
				strRead = dis.readUTF();
				System.out.println("Server says: "+strRead);
				
			}
			dos.close();
			System.out.println("Close connection");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SocketClient socketClient = new SocketClient("localhost", 5000);
	}

}
