package by.htp.ex.util.validation;

import java.util.Map;

public interface UserDataValidation {

	boolean checkRegistrationData(String login, String password, String confirmPassword, String email);

	Map<String, String> getInvalidRegistrationData();
}
