package lesson3.Socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class LowPortScanner {

	public static void main(String[] args) {
		String[] hostnames = {"www.hcmuaf.edu.vn","mail.hcmuaf.edu.vn","fit.hcmuaf.edu.vn","dkmh.hcmuaf.edu.vn"};
		
		for(int i =0; i < hostnames.length; i++) {
		try {
			Socket socket = new Socket(hostnames[i], 80);
			System.out.println("==>Connected to remote: "+ socket.getInetAddress() +"\n"
					+ "Port: "+ socket.getPort()+ "\tLocalPort: "+ socket.getLocalPort()  + "\n"
					+ "getReceiveBufferSize: " + socket.getReceiveBufferSize() +
					"\ngetSendBufferSize: " + socket.getSendBufferSize()+
					"\nLocalAddress: " + socket.getLocalAddress()+
					"\nSoTimeOut: " + socket.getSoTimeout()+
					"\nRemoteSocketAddress" + socket.getRemoteSocketAddress());
			
		} catch (UnknownHostException e) {
			System.out.println("unknown host"+e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		}
		
	}

}
