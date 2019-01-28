package review.finalexam.rmi3;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;

import review.finalexam.Exercise20.MyException;

public class CaculateIml extends UnicastRemoteObject implements ICaculate {
//	PrintWriter netOut;
	double operand1, operand2, result;
	char operate;

	protected CaculateIml() throws RemoteException {
		super();

		// TODO Auto-generated constructor stub
	}

	@Override
	public String connect() throws RemoteException {
		return "connect success...";
	}

	@Override
	public String start(String line) throws RemoteException {
		try {
			analysis(line);

			operate();

			line = line + " = " + result;
		} catch (MyException ex) {
			line = ex.getMessage();
		}

		return line;
	}

	private void operate() {
		switch (operate) {
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

	private void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line, "+-*/");
		String temp = token.nextToken();
		operate = line.charAt(temp.length());
		try {
			operand1 = Double.parseDouble(temp.trim());
		} catch (NumberFormatException ex) {
			throw new MyException("operand 1 is not valid!");
		}
		temp = token.nextToken();
		try {
			operand2 = Double.parseDouble(temp.trim());
		} catch (NumberFormatException ex) {
			throw new MyException("operand 2 is not valid!");
		}
	}

	@Override
	public void close() throws RemoteException {

	}

}
