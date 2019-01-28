package review.finalexam.Exercise20;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
/*20.	(8)Viết server hỗ trợ các phép tính cơ bản (+-* /) 
/*client tương tác với server bằng giao thức TCP thông qua các dòng văn bản. Người dùng nhập lệnh trên client có dạng:
1 + 2 rồi nhấp enter, client gửi dòng dữ liệu tới server, server trả về kết quả có dạng:
1 + 2 = 3; xử lý các ngoại lệ các toán hạng không phải là số và phép chia cho 0.
*/
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8);
		while(true) {
			Socket socket = server.accept();
			OneConnection connection = new OneConnection(socket);
			connection.start();
		}
	}

}
