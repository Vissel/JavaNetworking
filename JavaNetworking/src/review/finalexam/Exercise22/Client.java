package review.finalexam.Exercise22;

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
		Socket socket = new Socket("127.0.0.1", 10);
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(socket.getOutputStream(), true);
		
		String line;
		Scanner sc = new Scanner(System.in);
		while((line = netIn.readLine()) !=null) {
			if(line.equalsIgnoreCase("end")) break;
			System.out.println(line);
			line = sc.nextLine();
			netOut.println(line);
		}

	}

}