package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSIgnIn implements Command {
	private final static Logger logger = LogManager.getRootLogger();

	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;

		login = request.getParameter(Constant.JSP_LOGIN_PARAM);
		password = request.getParameter(Constant.JSP_PASSWORD_PARAM);

		// List<News> latestNews = null;

		try {

			// latestNews = newsService.latestList(5);
			String role = service.signIn(login, password);

			if (!role.equals("guest")) {

				request.getSession(true).setAttribute("user", "active");
				request.getSession(true).setAttribute("role", role);
				request.getSession().setAttribute("AuthenticationError", null);
				request.getSession().setAttribute("registration_status", null);
				response.sendRedirect("controller?command=go_to_news_list");

			} else {

				request.getSession(true).setAttribute("user", "not active");
				request.getSession().setAttribute("AuthenticationError", "wrong login or password");
				// request.setAttribute("news", latestNews);
				logger.log(Level.INFO, login);
				response.sendRedirect("controller?command=go_to_base_page");
			}

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
		}

	}

}
