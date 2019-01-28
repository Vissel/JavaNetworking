package lesson3.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class Exercise20MrTinhConnection extends Thread {
	PrintWriter netOut;
	BufferedReader netIn;
	Socket serverSocket;

	char operator;
	double operand1, operand2, resultOperator;

	public Exercise20MrTinhConnection(Socket socket) throws IOException {
		serverSocket = socket;
		netIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		netOut = new PrintWriter(serverSocket.getOutputStream(), true);

		netOut.println("Welcome ...");
	}

	public void run() {

		try {
			while (true) {
				// 1.request
				String line = netIn.readLine();
				if (line.equalsIgnoreCase("exit")) {
					break;
				} else {
					// 2.request anlytics
					try {
						requestAnlytic(line);
					
					// 3.caculator
					caculator();
					
					// 4.response
					line = line + "=" + resultOperator;
					netOut.println(line);
					
					}catch(Exercise20MyException e) {
						netOut.println(e.getMessage());
					}
				}
			}
			serverSocket.close();
		} catch (IOException e) {

		}
	}

	private void caculator() {
		switch (operator) {
		case '+':
			resultOperator = operand1 + operand2;
			break;
		case '-':
			resultOperator = operand1 - operand2;
			break;
		case '*':
			resultOperator = operand1 * operand2;
			break;
		case '/':
			resultOperator = operand1 / operand2;
			if(Double.isInfinite(resultOperator)) throw new Exercise20MyException("Error: operand 2 is 0, operator can't execute");
			break;
		}
	}

	private void requestAnlytic(String line) throws Exercise20MyException{
		// su dung StringTokenizer
		StringTokenizer token = new StringTokenizer(line, "+-*/");
		String tempOperand; // bien tam

		tempOperand = token.nextToken();
		operator = line.charAt(tempOperand.length()); // tách operator ra

		try {
		operand1 = Double.parseDouble(tempOperand);// tách operand 1
		}catch(NumberFormatException e) {
			throw new Exercise20MyException("Error: operand 1 is not number" );
		}
		
		tempOperand = token.nextToken();
		try {
			operand2 = Double.parseDouble(tempOperand); // tách operand 2
		} catch (NumberFormatException e) {
			throw new Exercise20MyException("Error: operand 2 is not number");
			
		}
	}
}
