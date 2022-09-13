package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {
	private final static Logger logger = LogManager.getRootLogger();
	private final ServiceProvider provider = ServiceProvider.getInstance();
	private final IUserService userService = provider.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(ControllerConstant.JSP_LOGIN_PARAM);
		String password = request.getParameter(ControllerConstant.JSP_PASSWORD_PARAM);
		String confirmPassword = request.getParameter(ControllerConstant.JSP_PASSWORD_CONFIRM_PARAM);
		String email = request.getParameter(ControllerConstant.JSP_EMAIL_PARAM);

		try {

			if (!userService.registration(login, password, confirmPassword, email)) {

				Map<String, String> invalidRegistrationData = userService.getInvalidRegistrationData(login, password,
						confirmPassword, email);
				request.getSession().setAttribute(ControllerConstant.JSP_INVALID_REGISTRATION_DATA_ATT,
						invalidRegistrationData);

			} else {

				request.getSession().setAttribute(ControllerConstant.JSP_REGISTRATION_STATUS_ATT,
						ControllerConstant.JSP_REGISTRATION_STATUS_ATT_SUCCESS);
			}
			response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);

		} catch (ServiceException e) {
			response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
			logger.log(Level.ERROR, e);
		}

	}

}
