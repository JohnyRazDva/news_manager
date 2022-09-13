package by.htp.ex.controller.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddNews implements Command {
	private final static Logger logger = LogManager.getRootLogger();
	private final ServiceProvider provider = ServiceProvider.getInstance();
	private final INewsService newsService = provider.getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter(ControllerConstant.JSP_NEWS_TITLE_PARAM);
		String brief = request.getParameter(ControllerConstant.JSP_NEWS_BRIEF_PARAM);
		String content = request.getParameter(ControllerConstant.JSP_NEWS_CONTENT_PARAM);
		LocalDate date = LocalDate.now();

		try {

			if (newsService.isNewsDataValid(title, brief, content)) {
				News news = new News(title, brief, content, date);
				if (newsService.add(news)) {
					request.getSession().setAttribute(ControllerConstant.JSP_ADD_NEWS_SUCCESS_PARAM, true);
				}
			} else {
				request.getSession().setAttribute(ControllerConstant.JSP_ADD_NEWS_SUCCESS_PARAM, false);
			}

			Map<String, String> invalidNewsData = newsService.getInvalidNewsData(title, brief, content);
			request.getSession(true).setAttribute(ControllerConstant.JSP_INVALID_NEWS_DATA_ATT, invalidNewsData);
			response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
		}
	}

}
