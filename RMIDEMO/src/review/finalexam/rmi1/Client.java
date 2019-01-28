package review.finalexam.rmi1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import review.finalexam.rmi1.IFileCopy;;

public class Client {
	public static void main(String[] args) throws RemoteException,NotBoundException, IOException {
		Registry  reg = LocateRegistry.getRegistry("127.0.0.1", 11);
		IFileCopy server = (IFileCopy) reg.lookup("copy");
		
		String srcFile = "D:\\eclipse-workspace\\data-test - Copy\\folder4\\pack1.zip";
		String desFile = "D:\\eclipse-workspace\\folder4\\pack1(copy).zip";
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
		server.createDes(desFile);
		byte[] byteSize = new byte[100*1024];
		int data;
		while((data = bis.read(byteSize)) != -1) {
			server.copy(data, byteSize);
		}
		bis.close();
		server.closeDes();
	}
}
