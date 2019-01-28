package review.finalexam.rmi4;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import review.finalexam.rmi4.IStudentList;;

public class Client {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 14);
		IStudentList server = (IStudentList) reg.lookup("info");
		Scanner sc = new Scanner(System.in);
		String line, dataReturn;
		while (true) {
			line = sc.nextLine();
			if (line.equalsIgnoreCase("exit"))
				break;

			dataReturn = server.login(line);
			if (dataReturn != null) {
				System.out.println(dataReturn);

				if (dataReturn.equals("login success!")) {
					while (true) {
						line = sc.nextLine();
						if (line.equalsIgnoreCase("exit"))
							break;
						List<String> list = server.execute(line);
						for (String str : list)
							System.out.println(str);
					}
				}
			} else
				System.out.println(line + " is not valid");
		}
	}

}
