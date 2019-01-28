package review.finalexam.rmi2;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileUpDownLoadIml extends UnicastRemoteObject implements IFileUpDownLoad{
	BufferedOutputStream bos; //upload
	DataInputStream dis ; //download
	
	protected FileUpDownLoadIml() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	//upload
	@Override
	public void createDes(String desFile) throws RemoteException {
		try {
			bos = new BufferedOutputStream(new FileOutputStream(desFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void upload(int data, byte[] byteRead) throws RemoteException {
		try {
			bos.write(byteRead, 0, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void closeDes() throws RemoteException {
		try {
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//download
	@Override
	public void createSrc(String srcFile) throws RemoteException {
		try {
			dis = new DataInputStream(new FileInputStream(srcFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int download(byte[] byteRead) throws RemoteException {
		int data = 0;
		try {
			data = dis.read(byteRead);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void closeSrc() throws RemoteException {
		try {
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
