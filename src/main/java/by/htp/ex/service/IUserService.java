package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.User;

public interface IUserService {

	String signIn(String login, String password) throws ServiceException;

	boolean registration(User user) throws ServiceException;

	List<String> getInvalidRegistrationData(User user) throws ServiceException;

}
