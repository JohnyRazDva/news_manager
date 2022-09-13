package by.htp.ex.util.validation;

import by.htp.ex.util.validation.impl.NewsValidationImpl;
import by.htp.ex.util.validation.impl.UserDataValidationImpl;

public class ValidationProvider {
	private static final ValidationProvider instance = new ValidationProvider();

	private final UserDataValidation userDataValidation = new UserDataValidationImpl();
	private final NewsValidation newsValidation = new NewsValidationImpl();

	private ValidationProvider() {
	};

	public static ValidationProvider getInstance() {
		return instance;
	}

	public UserDataValidation getUserDataValidation() {
		return userDataValidation;
	}

	public NewsValidation getNewsValidation() {
		return newsValidation;
	}

}
