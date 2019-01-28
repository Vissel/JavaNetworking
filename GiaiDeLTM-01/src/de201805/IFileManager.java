package de201805;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFileManager extends Remote{
	boolean setFolder(String line) throws RemoteException;
	String view(String line) throws RemoteException;
	boolean copy(String srcF, String desF) throws RemoteException;
	boolean move(String srcF, String desF) throws RemoteException;
	boolean rename(String srcF, String desF) throws RemoteException;
}
