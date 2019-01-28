package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFileUpload extends Remote{
	//upload
	void createDes(String desFile) throws RemoteException;
	void upload(byte[] data,int length) throws RemoteException;
	void closeDes() throws RemoteException;
	
	//download
	void createSrc(String srcFile) throws RemoteException;
	int download(byte[] data) throws RemoteException; 
	void closeSrc() throws RemoteException;
}
