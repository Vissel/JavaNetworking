package test.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class TestURL {
	//download one file from internet
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://drive.google.com/uc?id=0B92ekV0U21u1X196WTZkMkU2Qms&export=download");
		InputStream inputStream = url.openStream();
		
		BufferedInputStream dis = new BufferedInputStream( new DataInputStream(inputStream));
		BufferedOutputStream write = new BufferedOutputStream(new DataOutputStream(new FileOutputStream("D:\\eclipse-workspace\\data-test - Copy\\folder1\\windows10.iso")));
		
		byte[] byteSize = new byte[256];
		int data;
		while((data = dis.read(byteSize)) != -1) {
			write.write(byteSize, 0, data);
		}
		dis.close();
		write.close();
	}

}
