package review.finalexam.rmi4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	/*Viết CT su dung cong nghe RMI tra cứu thông tin sinh viên qua mạng có hỗ trợ đăng nhập tương tự POP3.
	 *  Dữ  liệu trên server lưu vào arraylist; Người dùng tương tác với client qua console với cú pháp lệnh: 
	user user_name
	pass password
	Sau khi đăng nhập thành công có thể thực hiện các thao tác:
	-	FindById id
	- findByName name
	- findByAge  age
	- findByScore score 
	*/
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(14);
		StudentListIml info = new StudentListIml();
		reg.rebind("info", info);
	}

}
