package review.finalexam.Exercise20;

import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
//		String st = "11 - 2222" ,s1,s2;
//		char s3;
//		StringTokenizer token = new StringTokenizer(st, "+-*/");
//		s1 = token.nextToken();
//		s3 = st.charAt(s1.length());
//		System.out.println(Double.parseDouble(s1));
//		s2 = token.nextToken();
//		System.out.println(Double.parseDouble(s2));
//		System.out.println(s3 );
		
		String line = "nguyen ngoc thach";
		StringTokenizer token = new StringTokenizer(line);
//		System.out.println(token.countTokens());
		String s ="";
		String s0 = token.nextToken();
		while(token.hasMoreTokens()) {
			s += token.nextToken()+ " ";
		}
		System.out.println(s0 + "\n" +s);
	}

}
