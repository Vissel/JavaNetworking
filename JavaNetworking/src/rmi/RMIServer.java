package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(12345); //port
		FileUploadImpl server = new FileUploadImpl();
		reg.rebind("upload", server);
		reg.rebind("download", server);
	}

}
