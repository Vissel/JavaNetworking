package review.finalexam.rmi3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICaculate extends Remote{
String connect()  throws RemoteException;
String start(String line) throws RemoteException;
void close() throws RemoteException;
}
