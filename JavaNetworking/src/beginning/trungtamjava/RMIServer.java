package beginning.trungtamjava;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements RMI{

	protected RMIServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Override
	public String helloWorld(String name) throws RemoteException {
		System.out.println("ten la: "+ name);
		return "xin chao"+ name;
	}
	
	public static void main(String[] args) {
		try {
			Naming.rebind("rmi://localhost:5000/helloWorld", new RMIServer());
			System.out.println("Server realdy");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
