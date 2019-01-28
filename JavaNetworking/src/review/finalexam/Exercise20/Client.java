package review.finalexam.Exercise20;

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
		Socket socket = new Socket("127.0.0.1", 8);
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		String line;
		while ((line = netIn.readLine())!= null) {
			if(line.equalsIgnoreCase("end")) break;
			System.out.println(line);
			Scanner sc = new Scanner(System.in);
			line = sc.nextLine();
			netOut.println(line);
		}
	}
}
