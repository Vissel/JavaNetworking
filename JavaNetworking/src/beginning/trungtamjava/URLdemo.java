package beginning.trungtamjava;

import java.net.MalformedURLException;
import java.net.URL;

public class URLdemo {
public static void main(String[] args) {
	try {
		URL url = new URL("http://trangsucjade.com/san-pham/lac-tay-bac-nu-venus-silver/");
		
		System.out.println(url.getHost());
		System.out.println(url.getProtocol());
		System.out.println(url.getPort());
		System.out.println(url.getPath());
		System.out.println(url.getFile());
		System.out.println(url.getAuthority());
		System.out.println(url.getQuery());
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
}
