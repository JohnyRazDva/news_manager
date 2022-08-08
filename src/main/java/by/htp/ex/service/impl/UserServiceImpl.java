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

		/*
		 * if(!userDataValidation.checkAUthData(login, password)) { throw new
		 * ServiceException("login ...... "); }
		 */

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
			if (isRegistrationDataValid(user)) {
				return userDAO.registration(user);
			} else {

				return false;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	private boolean isRegistrationDataValid(User user) throws ServiceException {
		return userDataValidation.checkRegistrationData(user);
	}

	@Override
	public List<String> getInvalidRegistrationData() {
		return userDataValidation.getInvalidRegistrationData();
	}

}
