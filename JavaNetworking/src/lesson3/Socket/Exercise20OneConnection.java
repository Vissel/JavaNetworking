package lesson3.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Exercise20OneConnection extends Thread {
	double result, operand1, operand2;
	String[] operator = { "+", "-", "*", "/" };
	String current;//operator xac dinh dc
	
	PrintWriter output;
	BufferedReader input;
	Socket serverSocket;

	public Exercise20OneConnection(Socket socket) throws IOException {
		serverSocket = socket;
		output = new PrintWriter(serverSocket.getOutputStream(), true);
		input = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		output.println("Welcome...");
	}

	public void run() {
		try {
			// 1.request
			while (true) {
				String line = input.readLine();
				if (line.equals("exit")) {
					break;
				} else {
					try {
					// 2.request analytics
					requestAnalytics(line);

					// 3.caculator
					caculator();

					// 4.response
					line = line + " = " + result;
					output.println("Server: Result:"+line);
					}catch(Exercise20MyException e) {
						output.println(e.getMessage());
					}
				}
			}
			serverSocket.close();
		} catch (IOException e) {

		}
	}

	private void caculator() {
		switch (current) {
		case "+":
			result = operand1 + operand2;
			break;
		case "-":
			result = operand1 - operand2;
			break;
		case "*":
			result = operand1 * operand2;
			break;
		case "/":
			result = operand1 / operand2;
			if(Double.isInfinite(result)) throw new Exercise20MyException("Error: not operator because operand 2 is 0");
			break;
		}
	}

	private void requestAnalytics(String line) throws Exercise20MyException {
		String replace;
		String[] split;
		for (String c : operator) {
			if (line.contains(c)) {
				replace = line.replace(c, "/");
				split = replace.split("/");
				try {
				operand1 = Double.parseDouble(split[0]);
				}catch(NumberFormatException e) {
					throw new Exercise20MyException("Error: operand 1 isn't a number");
				}
				try {
				operand2 = Double.parseDouble(split[1]);
				}catch(NumberFormatException e) {
					throw new Exercise20MyException("Error: operand 2 isn't a number");
				}
				current = c;
			}
		}
	}

}
