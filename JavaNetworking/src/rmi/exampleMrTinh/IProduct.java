package rmi.exampleMrTinh;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProduct extends Remote{
public String getDescription() throws RemoteException;
}
