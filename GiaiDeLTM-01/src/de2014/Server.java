package de2014;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(1212);
		ProductManager product = new ProductManager();
		reg.rebind("product", product);

	}

}
