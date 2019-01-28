package de2009;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class OneConnection extends Thread {
	public Socket serverSocket;
	DataInputStream dis;
	DataOutputStream dos;
	BufferedOutputStream bos; // send
	BufferedInputStream bis; // get
	String destDir = "D:\\eclipse-workspace\\GiaiDeLTM-01\\dest";
	String srcFile, destFile;

	public OneConnection(Socket socket) throws IOException {
		this.serverSocket = socket;
		dis = new DataInputStream(serverSocket.getInputStream());
		dos = new DataOutputStream(serverSocket.getOutputStream());
	}

	@Override
	public void run() {
		try {
			int number;
			while (true) {
				number = dis.readInt();// read number
				// todo
				switch (number) {
				case 1:
					setServerDir();
					break;
				case 3:
					sendFile();
					break;
				case 4:
					getFile();
					break;
				case 0:
					dis.close();
					dos.close();
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void getFile() throws IOException {
		String srcF = dis.readUTF();
		File file = new File(destDir + "\\" + srcF);
		if (!file.exists())
			System.out.println("file not found");
		else {
			dos.writeLong(file.length());
			bis = new BufferedInputStream(new FileInputStream(file));
			int data;
			while ((data = bis.read()) != -1) {
				dos.write(data);
			}
			bis.close();
		}
	}

	private void sendFile() throws IOException {
		String destF = dis.readUTF();
		long fileSize = dis.readLong();
		bos = new BufferedOutputStream(new FileOutputStream(destDir + "\\" + destF));
		int data;
		for (int i = 0; i < fileSize; i++) {
			data = dis.read();
			bos.write(data);
		}
		bos.close();
	}

	private void setServerDir() throws IOException {
		String direct = dis.readUTF();
		File dir = new File(destDir);
		if (!dir.exists())
			dir.mkdirs();
		this.destDir = direct;

		dos.writeBoolean(true);
	}

}
