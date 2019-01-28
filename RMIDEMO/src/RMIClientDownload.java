import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.IFileUpload;

public class RMIClientDownload {
	public static void download(String srcFile, String desFile) throws RemoteException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 12345);
		try {
			IFileUpload server = (IFileUpload) reg.lookup("download");

			server.createSrc(srcFile);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
			byte[] data = new byte[100 * 1024];
			int readData;
			while ((readData = server.download(data)) != -1) {
				bos.write(data, 0, readData);
			}
			server.closeSrc();
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws RemoteException {
		String srcFile = "D:\\Sublime Text 3\\python3.3.zip";
		String desFile = "D:\\eclipse-workspace\\data-test - Copy\\folder2\\python3.3(download).zip";
		download(srcFile, desFile);
	}

}
