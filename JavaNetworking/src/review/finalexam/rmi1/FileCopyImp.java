package review.finalexam.rmi1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileCopyImp extends UnicastRemoteObject implements IFileCopy{
//	BufferedReader netIn;
	BufferedOutputStream bos;
	protected FileCopyImp() throws RemoteException {
		super();
	}

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
	public void copy(int data, byte[] byteSize) throws RemoteException {
		try {
			bos.write(byteSize, 0, data);
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

}
