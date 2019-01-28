package review.finalexam.rmi3;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	/*	Viết server hỗ trợ các phép tính cơ bản (+-* /) ap dung Công nghệ RMI
	/*client tương tác với server thông qua các dòng văn bản. Người dùng nhập lệnh trên client có dạng:
	1 + 2 rồi nhấp enter, client gửi dòng dữ liệu tới server, server trả về kết quả có dạng:
	1 + 2 = 3; xử lý các ngoại lệ các toán hạng không phải là số và phép chia cho 0.
	*/
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(13);
		CaculateIml caculate = new CaculateIml();
		reg.rebind("caculate", caculate);
	}

}
