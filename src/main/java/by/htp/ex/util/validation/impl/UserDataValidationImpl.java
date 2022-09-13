package by.htp.ex.util.validation.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import by.htp.ex.util.validation.UserDataValidation;

public class UserDataValidationImpl implements UserDataValidation {
	private final static String LOGIN_PATTERN = "^[a-zA-Z][\\w]{6,14}";
	private final static String PASSWORD_PATTERN = "[\\w]{6,14}";
	private final static String EMAIL_PATTERN = "^(.+)@(.+)$";
	private final static String LOGIN_PARAM_FOR_LIST = "login";
	private final static String PASSWORD_PARAM_FOR_LIST = "password";
	private final static String CONFIRM_PASSWORD_PARAM_FOR_LIST = "confirmPassword";
	private final static String EMAIL_PARAM_FOR_LIST = "email";
	private Map<String, String> invalidRegistrationData;

	@Override
	public boolean checkRegistrationData(String login, String password, String confirmPassword, String email) {
		Map<String, String> invalidData = new HashMap();

		if (!checkLogin(login)) {
			invalidData.put(LOGIN_PARAM_FOR_LIST, LOGIN_PARAM_FOR_LIST);
		}
		if (!checkPassword(password)) {
			invalidData.put(PASSWORD_PARAM_FOR_LIST, PASSWORD_PARAM_FOR_LIST);
		}
		if (!checkConfirmPassword(password, confirmPassword)) {
			invalidData.put(CONFIRM_PASSWORD_PARAM_FOR_LIST, CONFIRM_PASSWORD_PARAM_FOR_LIST);
		}
		if (!checkEmail(email)) {
			invalidData.put(EMAIL_PARAM_FOR_LIST, EMAIL_PARAM_FOR_LIST);
		}
		invalidRegistrationData = invalidData;

		return checkLogin(login) && checkPassword(password) && checkConfirmPassword(password, confirmPassword)
				&& checkEmail(email);
	}

	@Override
	public Map<String, String> getInvalidRegistrationData() {
		return invalidRegistrationData;
	}

	private boolean checkLogin(String login) {
		return Pattern.compile(LOGIN_PATTERN).matcher(login).matches();
	}

	private boolean checkPassword(String password) {
		return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
	}

	private boolean checkEmail(String email) {
		return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
	}

	private boolean checkConfirmPassword(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}

}
