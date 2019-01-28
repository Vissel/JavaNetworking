package review.finalexam.Exercise20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class OneConnection extends Thread {
	Socket serverSocket;
	BufferedReader netIn;
	PrintWriter netOut;
	char operator;
	double operand1, operand2, result;

	public OneConnection(Socket socket) throws IOException {
		this.serverSocket = socket;
		netIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		netOut = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()), true);
		netOut.println("welcome");

	}

	@Override
	public void run() {
		try {
			while (true) {
				String line = netIn.readLine();
				if (line.equalsIgnoreCase("exit") || line == null) {
					netOut.println("end");
					break;
				}
				try {
					// split
					analysis(line);
					// operator
					operate();
					// send result
					line = line + " = " + result;
					netOut.println(line);
				} catch (MyException myEx) {
					netOut.println(myEx.getMessage());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void operate() {
		switch (operator) {
		case '+':
			result = operand1 + operand2;
			break;
		case '-':
			result = operand1 - operand2;
			break;
		case '*':
			result = operand1 * operand2;
			break;
		case '/':
			result = operand1 / operand2;
			if(Double.isInfinite(result)) throw new MyException("Error: operand 2 is 0. Can't operate ! Please enter again !");
			break;
		default:
			result = 0;
			break;
		}

	}

	private void analysis(String line) throws MyException{
		StringTokenizer token = new StringTokenizer(line, "+-*/");
		String tmp = token.nextToken();
		operator = line.charAt(tmp.length()); // operator
		try {
			operand1 = Double.parseDouble(tmp.trim()); // operand 1
		} catch (NumberFormatException n) {
			throw new MyException("Error: operand 1 is not a operand");
		}
		
		tmp = token.nextToken();
		
		try {
			operand2 = Double.parseDouble(tmp.trim()); // operand 2
		} catch (NumberFormatException n) {
			throw new MyException("Error: operand 2 is not a operand");
		}
	}
}
