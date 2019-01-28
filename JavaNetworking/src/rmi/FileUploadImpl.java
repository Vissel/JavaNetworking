package rmi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileUploadImpl extends UnicastRemoteObject implements IFileUpload{
	private static OutputStream output;
	private static InputStream input;
	
	protected FileUploadImpl() throws RemoteException {
		super();
	}
	
	/*-----upload------ */
	@Override
	public void createDes(String desFile) throws RemoteException {
		try {
			output = new BufferedOutputStream(new FileOutputStream(desFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upload(byte[] data, int length) throws RemoteException {
		try {
			output.write(data, 0, length);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void closeDes() throws RemoteException {
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*-----download------ */
	private static File file ;
	@Override
	public void createSrc(String srcFile) throws RemoteException {
		try {
			file = new File(srcFile);
			input = new DataInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int download(byte[] data) throws RemoteException {
		int readData =0;
		try {
			readData = input.read(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readData;
	}

	@Override
	public void closeSrc() throws RemoteException {
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
