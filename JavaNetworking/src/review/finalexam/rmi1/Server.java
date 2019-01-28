package review.finalexam.rmi1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
/*copy file qua mang */
	
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(11);
		FileCopyImp copy = new FileCopyImp();
		reg.rebind("copy", copy);
	}

}
