package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteNews implements Command {
	private final static Logger logger = LogManager.getRootLogger();
	private final ServiceProvider provider = ServiceProvider.getInstance();
	private final INewsService newsService = provider.getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			if (request.getParameter(ControllerConstant.JSP_ID_PARAM) != null) {
				int id = Integer.parseInt(request.getParameter(ControllerConstant.JSP_ID_PARAM));
				newsService.delete(id);

			} else if (request.getParameterValues(ControllerConstant.JSP_NEWS_IDS_PARAM) != null) {

				newsService.deleteAllById(request.getParameterValues(ControllerConstant.JSP_NEWS_IDS_PARAM));
			}

			response.sendRedirect(ControllerConstant.REDIRECT_TO_NEWS_LIST_URL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(ControllerConstant.REDIRECT_TO_NEWS_LIST_URL);
		}
	}

}
