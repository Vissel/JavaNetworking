package rmi.exampleMrTinh;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProductImpl extends UnicastRemoteObject implements IProduct{
	private String name;
	protected ProductImpl(String name) throws RemoteException {
		this.name = name;
	}
	@Override
	public String getDescription() throws RemoteException{
		return name;
	}

}
