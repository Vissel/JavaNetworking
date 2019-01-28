package de201808;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		String line = "Nguyễn  1313.01 4 7 Thạch";
		String re = line.replaceAll("[^0-9,-\\.\t]", ",");
		String[] item = re.split(",");
		
		List<Double> listNumber = new ArrayList<>();
		try {
			String temp ;
			for(int i = 0 ; i < item.length; i++){
				System.out.println(item[i]);
			}
		} catch (NumberFormatException e) {
//			System.out.println(e.getMessage());
		}
//		for (Double d : listNumber)
//			System.out.println(d);
	}

}
