package de201808;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(22222);
		WordProcessing word = new WordProcessing();
		reg.rebind("word", word);

	}

}
