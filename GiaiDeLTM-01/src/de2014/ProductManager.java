package de2014;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProductManager extends UnicastRemoteObject implements IProductManager {
	DAO dao = new DAO();
	static String lastUser = null;
	static Product product;

	protected ProductManager() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws RemoteException {
//		System.out.println(new ProductManager().login("1\tthach\t90\t10000"));
	}

	@Override
	public String connect() throws RemoteException {
		return "WELCOME TO MANAGE PRODUCT SYSTEM";
	}

	@Override
	public String checkUser(String param) throws RemoteException {
		try {
			lastUser = dao.checkUser(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (lastUser != null)
			return "OK";
		else
			return "FALSE";
	}

	@Override
	public String login(String param) throws RemoteException {
		boolean check = false;
		try {
			if (lastUser != null) {
				check = dao.login(lastUser, param);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (check)
			return "OK";
		else
			return "FALSE";
	}

	public List<String> analysis(String line) {
		List<String> list = new ArrayList<>();
		StringTokenizer token = new StringTokenizer(line, "\t");
		String temp;
		while (token.hasMoreTokens()) {
			temp = token.nextToken().trim();
			list.add(temp);
		}
		return list;
	}

	@Override
	public boolean addP(String param) throws RemoteException {
		List<String> list = analysis(param);
		product = new Product();
		for(int i = 0 ; i < list.size(); i ++){
			product.id = Integer.parseInt(list.get(0));
			product.name = list.get(1);
			product.count = Integer.parseInt(list.get(2));
			product.price = Double.parseDouble(list.get(3));
		}
		boolean check = false;
		try {
			check = dao.addP(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public int removeP(String param) throws RemoteException {
		List<String> list = analysis(param);
		int count = 0,number;
		for(String str : list){
			try {
				number = dao.removeP(Integer.parseInt(str));
				if(number == 1)count++;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public boolean editP(String param) throws RemoteException {
		List<String> list = analysis(param);
		product = new Product();
		for(int i = 0 ; i < list.size(); i ++){
			product.id = Integer.parseInt(list.get(0));
			product.name = list.get(1);
			product.count = Integer.parseInt(list.get(2));
			product.price = Double.parseDouble(list.get(3));
		}
		boolean check = false;
		try {
			check = dao.editP(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public List<String> view(String param) throws RemoteException {
		List<String> list = analysis(param);
		List<String> listSP = new ArrayList<>();
		for(String str : list){
			try {
				str = dao.viewP(str);
				listSP.add(str);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listSP;
	}

}
