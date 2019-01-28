package lesson3.Socket;

import java.util.StringTokenizer;

public class Exercise {

	public static void main(String[] args) {
		String str = "11/3";
		
		String[] split = str.split("/");
		String x,y;
		x= split[0];
		y = split[1];
		System.out.println(x);
		System.out.println(y);
//		for(String s : split) System.out.println(s);
	}

}
