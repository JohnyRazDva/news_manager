package by.htp.ex.util.validation;

import java.util.List;

import by.htp.ex.bean.User;

public interface UserDataValidation {
       boolean checkAUthData(String login, String password);
       boolean checkRegistrationData(User user);
       List<String> getInvalidRegistrationData();
}
