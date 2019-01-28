package rmi.exampleMrTinh;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(123); // tao moi truong quang ba
		ProductImpl canon = new ProductImpl("CANON 600D");//tao doi tuong tu xa
		ProductImpl nikon = new ProductImpl("NIKON abc");
		reg.rebind("CANON", canon);
		reg.rebind("NIKON", nikon);
		//note: bản thân khi server run thì vẫn nuôi sống đối tượng đó nên nó vẫn chạy kết nối. 
	}

}
