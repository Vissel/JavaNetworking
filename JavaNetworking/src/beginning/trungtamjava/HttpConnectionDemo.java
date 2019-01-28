package beginning.trungtamjava;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionDemo {
public static void main(String[] args) throws IOException {
	URL url = new URL("http://trangsucjade.com/san-pham/lac-tay-bac-nu-venus-silver/");
	
	HttpURLConnection http = (HttpURLConnection) url.openConnection();
	
	for(int i =0; i < http.getHeaderFields().size(); i++) {
		System.out.println(http.getHeaderFieldKey(i) + " - " + http.getHeaderField(i) );
//		System.out.println(http.getPermission());
	}
}
}
