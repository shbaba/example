package model;

public class LoginEntity {
	private String userId;
	private String pass;
	
	public LoginEntity(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}

	public String getUserId() {
		return userId;
	}
	public String getPass() {
		return pass;
	}
}
