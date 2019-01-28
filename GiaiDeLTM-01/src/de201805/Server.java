package de201805;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(55555);
		FileManagerImpl file = new FileManagerImpl();
		reg.rebind("file", file);
	}

}
