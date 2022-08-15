package by.htp.ex.service.impl;

import java.util.List;

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

	@Override
	public String signIn(String login, String password) throws ServiceException {

		try {
			if (userDAO.logination(login, password)) {
				return userDAO.getRole(login, password);
			} else {
				return "guest";
			}

		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(User user) throws ServiceException {
		try {
			if (isUserDataValid(user)) {

				return userDAO.registration(user);

			} else {
				return false;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private boolean isUserDataValid(User user) throws DaoException {
		return isRegistrationDataValid(user) && !isUserExist(user) && !isEmailInUse(user);
	}

	private boolean isRegistrationDataValid(User user) {
		return userDataValidation.checkRegistrationData(user);
	}

	@Override
	public List<String> getInvalidRegistrationData(User user) throws ServiceException {
		List<String> invalidRegistartionData = userDataValidation.getInvalidRegistrationData();
		try {
			if (isUserExist(user)) {
				invalidRegistartionData.add("invalid login");
			}
			if (isEmailInUse(user)) {
				invalidRegistartionData.add("invalid email");
			}
			return invalidRegistartionData;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	private boolean isUserExist(User user) throws DaoException {
		return userDAO.isUserExist(user.getLogin());
	}

	private boolean isEmailInUse(User user) throws DaoException {
		return userDAO.isEmailInUse(user.getEmail());
	}

}
