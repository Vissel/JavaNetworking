package beginning.trungtamjava;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI extends Remote{

	public String helloWorld(String name) throws RemoteException;
}
