package beginning.greekforgreek;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
	
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	public SocketServer(int port) throws IOException {
		try {
			serverSocket = new ServerSocket(port);//mo server
			System.out.println("Server started....");
			System.out.println("Wait for client...");
			
			socket = serverSocket.accept();//chap nhan cho client connect
			System.out.println("Client accept");
			
			//take input for the client socket
			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String line ="";
			
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			Scanner sc = new Scanner(System.in);
			String sentMessage = "";
			
			while(!line.equals("over")) {
				//received message from client
				line = dis.readUTF();
				System.out.println("Client says: "+line);
				
				//sent message to client
				sentMessage = sc.nextLine();
				dos.writeUTF(sentMessage);
				dos.flush();
	
			}
			socket.close();
			serverSocket.close();
			System.out.println("Close connection");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		SocketServer server = new SocketServer(5000);
	}

}
