package review.finalexam.rmi2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
/*Viết CT client/server sd cong nghe RMI de upload va download file*/
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(12);
		FileUpDownLoadIml upload = new FileUpDownLoadIml();
		FileUpDownLoadIml download = new FileUpDownLoadIml();
		reg.rebind("upload", upload);
		reg.rebind("download", download);
	}

}
