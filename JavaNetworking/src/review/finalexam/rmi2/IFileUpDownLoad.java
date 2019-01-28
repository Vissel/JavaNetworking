package review.finalexam.rmi2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFileUpDownLoad extends Remote{
//upload
	void createDes(String desFile) throws RemoteException;
	void upload(int data, byte[] byteRead)throws RemoteException;
	void closeDes()throws RemoteException;
	
//download
	void createSrc(String srcFile) throws RemoteException;
	int download(byte[] byteRead) throws RemoteException;
	void closeSrc() throws RemoteException;
}
