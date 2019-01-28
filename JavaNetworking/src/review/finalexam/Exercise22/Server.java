package review.finalexam.Exercise22;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
/*22.	(9)Viết CT client/server ra cứu thông tin sinh viên qua mạng có hỗ trợ đăng nhập tương tự POP3.
 *  Dữ  liệu trên server lưu vào arraylist; Người dùng tương tác với client qua console với cú pháp lệnh: 
user user_name
pass password
Sau khi đăng nhập thành công có thể thực hiện các thao tác:
-	FindById id
- findByName name
- findByAge  age
- findByScore score
*/
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10);
		while(true) {
			Socket socket = server.accept();
			OneConnection connection = new OneConnection(socket);
			connection.start();
		}
	}

}
