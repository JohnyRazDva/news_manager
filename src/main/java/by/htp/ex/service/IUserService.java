package by.htp.ex.service;

import java.util.Map;

public interface IUserService {

	String signIn(String login, String password) throws ServiceException;

	boolean registration(String login, String password, String confirmPassword, String email) throws ServiceException;

	Map<String, String> getInvalidRegistrationData(String login, String password, String confirmPassword, String email)
			throws ServiceException;

}
