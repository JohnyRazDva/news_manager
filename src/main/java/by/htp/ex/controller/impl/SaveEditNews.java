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

public class SaveEditNews implements Command {
	private final static Logger logger = LogManager.getRootLogger();
	private final ServiceProvider provider = ServiceProvider.getInstance();
	private final INewsService newsService = provider.getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt((String) request.getSession().getAttribute(ControllerConstant.JSP_ID_PARAM));
		String title = request.getParameter(ControllerConstant.JSP_NEWS_TITLE_PARAM);
		String brief = request.getParameter(ControllerConstant.JSP_NEWS_BRIEF_PARAM);
		String content = request.getParameter(ControllerConstant.JSP_NEWS_CONTENT_PARAM);
		LocalDate date = LocalDate.now();
		News news = new News(title, brief, content, date);
		try {
			if (newsService.isNewsDataValid(title, brief, content)) {
				if (newsService.save(id, news)) {
					request.getSession().setAttribute(ControllerConstant.JSP_EDIT_NEWS_SUCCESS_PARAM, true);
					request.getSession().setAttribute(ControllerConstant.JSP_EDIT_NEWS_STATUS_PARAM, false);
				}
			} else {
				news.setIdNews(id);
				request.getSession().setAttribute(ControllerConstant.JSP_NEWS_ATT, news);
				request.getSession().setAttribute(ControllerConstant.JSP_EDIT_NEWS_SUCCESS_PARAM, false);
			}
			Map<String, String> invalidNewsData = newsService.getInvalidNewsData(title, brief, content);
			request.getSession().setAttribute(ControllerConstant.JSP_INVALID_NEWS_DATA_ATT, invalidNewsData);
			response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
		} catch (ServiceException e) {
			request.getSession().setAttribute(ControllerConstant.JSP_EDIT_NEWS_SUCCESS_PARAM, false);
			logger.log(Level.ERROR, e);
			response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
		}
	}

}
