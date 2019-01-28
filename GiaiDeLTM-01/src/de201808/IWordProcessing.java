package de201808;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IWordProcessing extends Remote {
	String getWelcomeMessage() throws RemoteException;

	String checkUser(String param) throws RemoteException;

	boolean login(String param) throws RemoteException;

	boolean addFile(String param ) throws RemoteException;
	boolean addText(String param ) throws RemoteException;
	double getSum() throws RemoteException;
	int getNums() throws RemoteException;
	int getWord() throws RemoteException;
	List<String> getNumList() throws RemoteException;
}
