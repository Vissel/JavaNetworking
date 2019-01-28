package lesson3.Socket;

import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		String str = "121*20";
		StringTokenizer stk = new StringTokenizer(str,"+-*/");
		
		String operand;
		double operand1, operand2;
		char operator;
		
		operand = stk.nextToken();
		operator = str.charAt(operand.length());
		System.out.println(operand);
		
		operand1 = Double.parseDouble(operand);
		
		operand = stk.nextToken();
		operand2 = Double.parseDouble(operand);
		System.out.println(operand);
		
//		double result = operand1 * operand2;
//		System.out.println("="+result);
	}

}
