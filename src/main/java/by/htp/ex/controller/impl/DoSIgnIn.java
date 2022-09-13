package by.htp.ex.controller.impl;

import java.io.IOException;

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

public class DoSIgnIn implements Command {
	private final static Logger logger = LogManager.getRootLogger();

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;

		login = request.getParameter(ControllerConstant.JSP_LOGIN_PARAM);
		password = request.getParameter(ControllerConstant.JSP_PASSWORD_PARAM);

		try {

			String role = service.signIn(login, password);

			if (!role.equals(ControllerConstant.GUEST_ROLE_VALUE)) {

				request.getSession(true).setAttribute(ControllerConstant.JSP_USER_ATT,
						ControllerConstant.JSP_USER_ATT_ACTIVE);
				request.getSession().setAttribute(ControllerConstant.JSP_ROLE_ATT, role);
				request.getSession().setAttribute(ControllerConstant.JSP_AUTHENTICATION_ERROR, null);

				response.sendRedirect(ControllerConstant.REDIRECT_TO_NEWS_LIST_URL);
				request.getSession().setAttribute(ControllerConstant.JSP_REGISTRATION_STATUS_ATT, null);

			} else {
				request.getSession(true).setAttribute(ControllerConstant.JSP_USER_ATT,
						ControllerConstant.JSP_USER_ATT_NOT_ACTIVE);
				request.getSession().setAttribute(ControllerConstant.JSP_AUTHENTICATION_ERROR,
						ControllerConstant.JSP_AUTHENTICATION_ERROR_VALUE);
				response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
			}

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
		}

	}

}
