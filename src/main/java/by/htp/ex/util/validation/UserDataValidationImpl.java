package by.htp.ex.util.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import by.htp.ex.bean.User;

public class UserDataValidationImpl implements UserDataValidation{
	private final static String LOGIN_PATTERN = "^[a-zA-Z][\\w]{6,14}";
	private final static String PASSWORD_PATTERN = "^[\\w]{6,14}";
	private final static String EMAIL_PATTERN = "^(.+)@(.+)$";
	private List<String>  invalidRegistrationData = new ArrayList();

	@Override
	public boolean checkAUthData(String login, String password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean checkRegistrationData(User user) {
		String login = user.getLogin();
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		String email = user.getEmail();
		List<String> invalidData = new ArrayList();
		
		if (!checkLogin(login)) {
			invalidData.add("invalid login");
		}
		if (!checkPassword(password, confirmPassword)) {
			invalidData.add("invalid password");
		}
		if (!checkEmail(email)) {
			invalidData.add("invalid email");
		}
		invalidRegistrationData = invalidData;
		
		return checkLogin(login) && checkPassword(password, confirmPassword) && checkEmail(email);
	}

	@Override
	public List<String> getInvalidRegistrationData() {
		return invalidRegistrationData;
	}
	
	private boolean checkLogin(String login) {
		return Pattern.compile(LOGIN_PATTERN).matcher(login).matches();
	}
	
	private boolean checkPassword(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
		return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
		}
		else {
			return false;
		}
	}
	
	private boolean checkEmail(String email) {
		return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
	}
	
	
}
