package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.bean.User;
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

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String email = request.getParameter("email");

		User user = new User(login, password, confirm_password, email);

		try {

			if (!userService.registration(user)) {

				List<String> invalidRegistrationData = userService.getInvalidRegistrationData(user);
				request.getSession().setAttribute("invalidRegistrationData", invalidRegistrationData);

			} else {

				request.getSession().setAttribute("registration_status", "success_done");
			}
			response.sendRedirect("controller?command=go_to_base_page");

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
		}

	}

}
