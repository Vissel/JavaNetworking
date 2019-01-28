package Socket.ex22;

public class User {
	String username;
	String password;
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public boolean checkUser(String param) {
		return username.equals(param);
	}
	public boolean checkLogin(String lastUser, String param) {
		return username.equals(lastUser) && password.equals(param);
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
