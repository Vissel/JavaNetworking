package de201805.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 55555);
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		String line;
		Scanner sc = new Scanner(System.in);
		while(true){
			line = netIn.readLine();
			if(line.equalsIgnoreCase("QUIT")) break;
			if(line.equals("VIEW")){
				int size= Integer.parseInt(netIn.readLine());
				for(int i = 0 ; i < size ; i++){
					line = netIn.readLine();
					System.out.println(line);
				}
				
			}
			System.out.println(line);
			line = sc.nextLine();
			netOut.println(line);
		}
		

	}

}
