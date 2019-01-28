package review.finalexam.rmi2;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import review.finalexam.rmi2.IFileUpDownLoad;

public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 12);
		IFileUpDownLoad upload = (IFileUpDownLoad) reg.lookup("upload");
		IFileUpDownLoad download = (IFileUpDownLoad) reg.lookup("download");
		
		//upload
/*		String srcFU = "D:\\eclipse-workspace\\data-test - Copy\\folder4\\pack1.zip";
		String desFU = "D:\\eclipse-workspace\\folder4\\pack1(upload).zip";
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFU));
		upload.createDes(desFU);
		byte[] byteRead = new byte[100*1024];
		int data;
		while((data = bis.read(byteRead)) != -1) {
			upload.upload(data, byteRead);
		}
		bis.close();
		upload.closeDes();
*/
		
		//download
		String srcFD = "D:\\eclipse-workspace\\data-test - Copy\\folder4\\pack1.zip";
		String desFD = "D:\\eclipse-workspace\\folder4\\pack1(download).zip";
		download.createSrc(srcFD);
		DataOutputStream dis = new DataOutputStream(new FileOutputStream(desFD));
		byte[] byteRead = new byte[100*1024];
		int data;
		while((data = download.download(byteRead)) != -1) {
			dis.write(byteRead, 0, data);
		}
		download.closeSrc();
		dis.close();
	}

}
