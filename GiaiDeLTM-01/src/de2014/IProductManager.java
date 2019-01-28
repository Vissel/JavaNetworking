package de2014;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IProductManager extends Remote{
String connect() throws RemoteException;
String checkUser(String param) throws RemoteException;
String login(String param)throws RemoteException;
boolean addP(String param) throws RemoteException;
int removeP(String param) throws RemoteException;
boolean editP(String param)throws RemoteException;
List<String> view(String param) throws RemoteException;

}
