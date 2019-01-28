import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.IFileUpload;
public class RMIClientUpload {
	public static void upload(String srcFile, String desFile) throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 12345);
		IFileUpload server = (IFileUpload) reg.lookup("upload");
		
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(srcFile));
			server.createDes(desFile);
			byte[] data = new byte[100*1024];
			int readByte ;
			while((readByte = input.read(data)) != -1) {
				server.upload(data, readByte);
			}
			input.close();
			server.closeDes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws RemoteException, NotBoundException {
		String srcFile = "D:\\eclipse-workspace\\data-test - Copy\\folder2\\apache-tomcat-8.5.35.zip";
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder2\\apache-tomcat-8.5.35(Copy).zip";
		upload(srcFile, desFile);
	}

}
