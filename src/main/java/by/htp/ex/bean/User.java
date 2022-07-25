package by.htp.ex.bean;

public class User {
private String login;
private String password;
private String confirmPassword;
private String email;

public User() {}

public User(String login, String password, String confirm_password, String email) {
	this.login = login;
	this.password = password;
	this.confirmPassword = confirm_password;
	this.email = email;
	
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


}
