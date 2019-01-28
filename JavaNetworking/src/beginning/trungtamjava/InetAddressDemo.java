package beginning.trungtamjava;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getByName("www.oxfordlearnersdictionaries.com");
		
		InetAddress[] arr = InetAddress.getAllByName("localhost");
		for(InetAddress i : arr) System.out.println(i);
		//		System.out.println(address);
//		System.out.println(address.getHostAddress());
//		System.out.println(address.getHostName());
//		System.out.println(address.getLocalHost());
//		System.out.println(address.getCanonicalHostName());
		
	}

}
