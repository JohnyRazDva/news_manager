package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

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

public class GoToNewsList implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static Logger logger = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList;
		try {
			newsList = newsService.list();
			request.setAttribute(ControllerConstant.JSP_NEWS_ATT, newsList);
			request.getSession().setAttribute(ControllerConstant.JSP_PRESENTATION_ATT,
					ControllerConstant.JSP_PRESENTATION_ATT_VALUE_NEWS_LIST);
			request.getSession().setAttribute(ControllerConstant.JSP_ADD_NEWS_STATUS_PARAM, false);
			request.getSession().setAttribute(ControllerConstant.JSP_ADD_NEWS_SUCCESS_PARAM, false);

			request.getRequestDispatcher(ControllerConstant.BASE_LAYOUT_PAGE_URL).forward(request, response);
		} catch (ServiceException e) {
			request.getRequestDispatcher(ControllerConstant.BASE_LAYOUT_PAGE_URL).forward(request, response);
			logger.log(Level.ERROR, e);
		}

	}

}
