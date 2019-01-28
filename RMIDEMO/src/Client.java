import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.exampleMrTinh.*;

public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 123);
		IProduct product = (IProduct) reg.lookup("NIKON");
		System.out.println(product.getDescription());
	}

}
