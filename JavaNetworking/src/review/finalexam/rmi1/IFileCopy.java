package review.finalexam.rmi1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFileCopy extends Remote{
	void createDes(String desFile) throws RemoteException;
	void copy(int data, byte[] byteSize)throws RemoteException;
	void closeDes()throws RemoteException;
}
