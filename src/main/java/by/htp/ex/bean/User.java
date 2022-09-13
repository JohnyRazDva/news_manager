package by.htp.ex.bean;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class User {
	private String login;
	private String password;
	private String confirmPassword;
	private String email;
	private UserAccess access = UserAccess.USER;

	public User() {
	}

	public User(String login, String password, String confirmPassword, String email) {
		this.login = login;
		// this.password = password;
		// this.confirmPassword = confirmPassword;
		setPassword(password);
		setConfirmPassword(confirmPassword);
		this.email = email;
	}

	public User(String login, String password) {
		this.login = login;
		setPassword(password);
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public String getAccess() {
		return access.toString();
	}

	private String encodingPassword(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
	}

	private void setPassword(String password) {
		this.password = encodingPassword(password);
	}

	private void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = encodingPassword(confirmPassword);
	}

}
