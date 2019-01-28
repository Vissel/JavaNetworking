package lesson3.Socket;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Exercise20Client {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 12345);

		PrintWriter netOut = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		System.out.println(netIn.readLine());
		Scanner scanner = new Scanner(System.in);

		while (true) {
			String lineEnter = scanner.nextLine();
			if (lineEnter.equals("exit")) {
				break;
			} else {
				netOut.println(lineEnter);
				System.out.println(netIn.readLine());
			}
		}
		socket.close();
	}

}
