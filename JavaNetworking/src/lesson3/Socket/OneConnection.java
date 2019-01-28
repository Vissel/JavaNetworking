package lesson3.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class OneConnection extends Thread {
	PrintWriter output;
	BufferedReader input;
	Socket socketServer;

	public OneConnection(Socket socket) throws IOException {
		socketServer= socket; 
		output = new PrintWriter(new OutputStreamWriter(socketServer.getOutputStream()), true);
		input = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));

		output.println("Welcome...");
	}

	@Override
	public void run() {
			try {
				String line;
				while ((line = input.readLine()) != null) {
					if (line.equals("exit")) {
						break;
					}
					line = "Echo: " + line;
					output.println(line);
				}
				socketServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
