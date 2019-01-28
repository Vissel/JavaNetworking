package de201805.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class OneConnection extends Thread {
	Socket serverSocket;
	BufferedReader netIn;
	PrintWriter netOut;
	String command, param;
	String dir = "";
	BufferedInputStream bis;
	BufferedOutputStream bos;

	public OneConnection(Socket socket) throws IOException {
		this.serverSocket = socket;
		netIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		netOut = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()), true);
		netOut.println("Welcome to File Manager");
	}

	@Override
	public void run() {
		String line;
		try {
			while (true) {
				line = netIn.readLine();
				if (line.equalsIgnoreCase("QUIT") || line == null) {
					netOut.println("QUIT");
					break;
				}
				analysis(line);

				boolean check;
				// todo
				try {
					switch (command.toUpperCase()) {
					case "SET_FOLDER":
						setFolder(param.trim());
						break;
					case "VIEW":
						List<String> listPath = view(param.trim());
						netOut.println("VIEW");
						netOut.println(listPath.size());
						for (String path : listPath)
							netOut.println(path);
						break;
					case "COPY":
						analysis(param);
						check = copy(command, param.trim());
						if (check)
							netOut.println("COPY success!");
						else
							netOut.println("COPY failed");
						break;
					case "MOVE":
						analysis(param);
						check = move(command, param.trim());
						if (check)
							netOut.println("MOVE success!");
						else
							netOut.println("MOVE failed");
						break;
					case "RENAME":
						analysis(param);
						check = rename(command, param.trim());
						if (check)
							netOut.println("RENAME success!");
						else
							netOut.println("RENAME failed");
						break;
					default:
						netOut.println("Command not found");
						break;
					}
				} catch (MyException e) {
					netOut.println(e.getMessage());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean rename(String srcF, String desF) throws IOException {
		return move(srcF, desF);
	}

	private boolean move(String srcF, String desF) throws IOException {
		File file = new File(srcF);
		if (!file.exists()) {
			netOut.println("file not exists");
			return false;
		} else {
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				throw new MyException("Access is denied");
			}
			bos = new BufferedOutputStream(new FileOutputStream(desF));
			byte[] byteRead = new byte[1024];
			int data;
			while ((data = bis.read(byteRead)) != -1) {
				bos.write(byteRead, 0, data);
			}
			bis.close();
			bos.close();
			file.delete();
			return true;
		}
	}

	private boolean copy(String srcF, String desF) throws IOException {
		File file = new File(srcF);
		if (!file.exists()) {
			netOut.println("file not exists");
			return false;
		} else {
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				throw new MyException("Access is denied");
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
		}
	}

	private List<String> view(String trim) throws IOException {
		List<String> listPath = new ArrayList<>();
		File folder = new File(trim);
		if (folder.isDirectory() && folder.exists()) {
			if (folder.length() == 0)
				listPath.add(folder.getPath());
			File[] list = folder.listFiles();
			for (File file : list) {
				if (file.isFile())
					listPath.add(file.getPath());
				if (file.isDirectory()) {
					listPath.add(file.getPath());
					view(file.getCanonicalPath());
				}
			}

		} else {
			// file
			File file = new File(dir + "\\" + trim);
			if (!file.exists())
				netOut.println("file not exists");
			else {
				String permision = "";
				if (file.canExecute())
					permision += "E";
				if (file.canRead())
					permision += "R";
				if (file.canWrite())
					permision += "W";
				listPath.add(file.getPath() + "\t" + file.length() + "\t" + permision);
			}
		}
		return listPath;
	}

	private void setFolder(String trim) {
		File file = new File(trim);
		if (!file.exists())
			netOut.println("Error: path not exists");
		if (file.isFile())
			netOut.println("Error: path is a file, not folder");
		if (file.isDirectory()) {
			this.dir = trim;
			netOut.println("Set folder success!");
		}
	}

	private void analysis(String line) {
		StringTokenizer token = new StringTokenizer(line, "| ");
		command = "";
		command = token.nextToken().trim();
		param = "";
		while (token.hasMoreTokens()) {
			param += token.nextToken().trim() + " ";
		}
	}
}
