package by.htp.ex.service.impl;

import java.util.Map;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	private final UserDataValidation userDataValidation = ValidationProvider.getInstance().getUserDataValidation();
	private static final String GUEST_VALUE = "guest";
	private static final String LOGIN_USED_PARAM_FOR_LIST = "loginUsed";
	private static final String EMAIL_USED_PARAM_FOR_LIST = "emailUsed";

	@Override
	public String signIn(String login, String password) throws ServiceException {
		User user = new User(login, password);
		try {
			if (userDAO.logination(user)) {
				return userDAO.getRole(user);
			} else {
				return GUEST_VALUE;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(String login, String password, String confirmPassword, String email)
			throws ServiceException {
		try {
			if (isUserDataValid(login, password, confirmPassword, email)) {
				User user = new User(login, password, confirmPassword, email);
				return userDAO.registration(user);
			} else {
				return false;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private boolean isUserDataValid(String login, String password, String confirmPassword, String email)
			throws DaoException {
		return isRegistrationDataValid(login, password, confirmPassword, email) && !isUserExist(login)
				&& !isEmailInUse(email);
	}

	private boolean isRegistrationDataValid(String login, String password, String confirmPassword, String email) {
		return userDataValidation.checkRegistrationData(login, password, confirmPassword, email);
	}

	@Override
	public Map<String, String> getInvalidRegistrationData(String login, String password, String confirmPassword,
			String email) throws ServiceException {
		Map<String, String> invalidRegistartionData = userDataValidation.getInvalidRegistrationData();
		try {
			if (isUserExist(login)) {
				invalidRegistartionData.put(LOGIN_USED_PARAM_FOR_LIST, LOGIN_USED_PARAM_FOR_LIST);
			}
			if (isEmailInUse(email)) {
				invalidRegistartionData.put(EMAIL_USED_PARAM_FOR_LIST, EMAIL_USED_PARAM_FOR_LIST);
			}
			return invalidRegistartionData;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private boolean isUserExist(String login) throws DaoException {
		return userDAO.isUserExist(login);
	}

	private boolean isEmailInUse(String email) throws DaoException {
		return userDAO.isEmailInUse(email);
	}
}
