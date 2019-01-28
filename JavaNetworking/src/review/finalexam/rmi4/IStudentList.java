package review.finalexam.rmi4;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStudentList extends Remote{
	String login(String line) throws RemoteException;
	List<String> execute(String line) throws RemoteException; 
}
