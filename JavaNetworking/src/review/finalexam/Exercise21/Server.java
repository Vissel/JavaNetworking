package review.finalexam.Exercise21;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
/*21.	Viết CT client/server ra cứu thông tin sinh viên qua mạng. Dữ  liệu trên server lưu vào arraylist; 
 * Người dùng tương tác với client qua console với cú pháp lệnh: 
findByName name
findByAge  age
findByScore score
*/
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9);
		while(true) {
			Socket socket = server.accept();
			OneConnection connection = new OneConnection(socket);
			connection.start();
		}
	}

}
