package Socket.ex22;

public class Test {
	public static boolean test(String command, String param) {
		boolean loop = false;
		while (!loop) {
			switch (command) {
			case "USER":
				if (param.equals("thach")) {
					System.out.println("thach");
					loop = true;
				} else
					System.out.println("failed user");
				break;

			default:
				System.out.println("default");
				break;
			}
			System.out.println("a");
//			break;
		}
		return true;
	}
	public static void main(String[] args) {
		String command = "USER";
		String param = "thachh";
		test(command, param);
	}

}
