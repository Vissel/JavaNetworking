package de201805;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.ReflectPermission;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileManagerImpl extends UnicastRemoteObject implements IFileManager {
	String dir = "";
	BufferedInputStream bis;
	BufferedOutputStream bos;

	protected FileManagerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean setFolder(String line) throws RemoteException {
		File file = new File(line);
		if (!file.exists() || file.isFile())
			return false;
		else {
			dir = file.getPath();
			return true;
		}
	}

	@Override
	public String view(String line) throws RemoteException {
		File file = new File(line);
		String path = "";
		if (file.isDirectory() && file.exists()) {
			if (file.length() == 0)
				path += file.getPath() + "\n";
			File[] listFile = file.listFiles();
			for (File f : listFile) {
				if (f.isFile())
					path += f.getPath() + "\n";
				if (f.isDirectory()) {
					try {
						path += f.getPath() + "\n";
						view(f.getCanonicalPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			File f = new File(dir + "\\" + line);
			if(!file.exists()) return "path not exist";
			String rw = "";
			if (f.canRead())
				rw = "R";
			if (f.canWrite())
				rw = rw + "W";
			if (f.canExecute())
				rw = rw + "E";
			return f.getPath() + "\t" + f.length() + rw;
		}
		return path;
	}

	@Override
	public boolean copy(String srcF, String desF) throws RemoteException {
		try {
			File f = new File(srcF);
			if(!f.exists()) return false;
			try{
			bis = new BufferedInputStream(new FileInputStream(f));
			}catch(FileNotFoundException e){
				throw new MyException("Access is denied");
//				restart();
			}
			bos = new BufferedOutputStream(new FileOutputStream(desF));
			byte[] byteRead = new byte[1024];
			int data;
			while ((data = bis.read(byteRead)) != -1) {
				bos.write(byteRead, 0, data);
			}
			bis.close();
			bos.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean move(String srcF, String desF) throws RemoteException {
		try {
			File f = new File(srcF);
			if(!f.exists()) return false;
			bis = new BufferedInputStream(new FileInputStream(f));
			bos = new BufferedOutputStream(new FileOutputStream(desF));
			byte[] byteRead = new byte[1024];
			int data;
			while ((data = bis.read(byteRead)) != -1) {
				bos.write(byteRead, 0, data);
			}
			bis.close();
			bos.close();
			f.delete();
			return true;
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean rename(String srcF, String desF) throws RemoteException {
		return move(srcF, desF);
	}
	
	public void restart() throws RemoteException{
		FileManagerImpl f = new FileManagerImpl();
	}
}
