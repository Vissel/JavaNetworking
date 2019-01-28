package review.finalexam.rmi3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import review.finalexam.rmi3.ICaculate;;

public class Client {
/*Doi voi Cong nghe RMI. ko nhat thiet phai su dung BufferedRead or PrintWriter de truyen du lieu.
 * Co the dung param de truyen tham so den server, va data return de tra ve client
 * */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 13);
		ICaculate server = (ICaculate) reg.lookup("caculate");
//		BufferedReader netIn = new BufferedReader(new InputStreamReader(System.in));
		String con = server.connect();
		System.out.println(con);
		String line, dataReturn;
		Scanner sc = new Scanner(System.in);
		try {
			while (true) {
//				line = netIn.readLine();
//				System.out.println(line);
				line = sc.nextLine();
				if (line.equalsIgnoreCase("exit"))
					break;
				dataReturn = server.start(line);
				System.out.println(dataReturn);
			}
//			netIn.close();
			server.close();
		} catch (IOException e) {

		}
	}

}
