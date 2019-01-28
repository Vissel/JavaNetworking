package de2009;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	BufferedInputStream bis; // send
	BufferedOutputStream bos; //get
	
	static String command, param;
	String sourceDir = "D:\\eclipse-workspace\\GiaiDeLTM-01\\source";

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
		client.connection();
		Scanner sc = new Scanner(System.in);
		String line;
		while (true) {
			line = sc.nextLine();
			if (line.equalsIgnoreCase("QUIT")){
				client.dos.writeInt(0);
				break;
			}
			client.analysis(line);

			switch (command.toUpperCase()) {
			case "SET_SERVER_DIR":
				boolean setSD = client.setServerDir(param);
				System.out.println(setSD);
				break;
			case "SET_CLIENT_DIR":
				client.setClientDir(param);
				System.out.println("set clinent dir is: " + param);
				break;
			case "SEND":
				client.send(param.trim());
				break;
			case "GET":
				client.get(param.trim());
				break;
			default:
				break;
			}
			// return ...
		}
		client.close();
	}

	private void get(String param2) throws IOException {
		dos.writeInt(4);
		analysis(param2);
		String srcFile = command.trim();//serverF
		String desFile = param.trim();//clientF
		dos.writeUTF(srcFile);
		
		long fileSize = dis.readLong();//filesize
		bos = new BufferedOutputStream(new FileOutputStream(sourceDir+"\\"+desFile));
		int data;
		for(int i = 0 ; i <fileSize; i++){
			data = dis.read();
			bos.write(data);
		}
		bos.close();
	}

	private void send(String param2) throws IOException {
		dos.writeInt(3); // send number 3
		analysis(param2);
		String srcFile = command.trim();
		String desFile = param.trim();
		dos.writeUTF(desFile);// send destF
		File file = new File(sourceDir + "\\" + srcFile);
		if (!file.exists())
			System.out.println("file not found");
		else {
			dos.writeLong(file.length()); // send sizeFile
			bis = new BufferedInputStream(new FileInputStream(file));
			int data;
			while ((data = bis.read()) != -1) {
				dos.write(data);// write
			}
			bis.close();
		}
	}

	private void setClientDir(String param2) {
		this.sourceDir = param2;
	}

	private boolean setServerDir(String param2) throws IOException {
		dos.writeInt(1);// write number 1
		dos.writeUTF(param2);

		boolean todo = dis.readBoolean();
		if (todo)
			return true;
		return false;
	}

	private void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line);
		command = "";
		command = token.nextToken().trim();
		param = "";
		while (token.hasMoreTokens()) {
			param += token.nextToken() + " ";
		}

	}

	public void connection() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 12345);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
	}

	public void close() throws IOException {
		dis.close();
		dos.close();
		socket.close();
	}
}
