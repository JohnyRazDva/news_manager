package by.htp.ex.controller.impl;

import java.io.IOException;

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

public class GoToViewNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static Logger logger = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		String id;
		id = request.getParameter(ControllerConstant.JSP_ID_PARAM);

		try {
			news = newsService.findById(Integer.parseInt(id));
			request.getSession().setAttribute(ControllerConstant.JSP_NEWS_ATT, news);
			request.getSession().setAttribute(ControllerConstant.JSP_PRESENTATION_ATT,
					ControllerConstant.JSP_PRESENTATION_ATT_VALUE_VIEV_NEWS);
			request.getRequestDispatcher(ControllerConstant.BASE_LAYOUT_PAGE_URL).forward(request, response);
		} catch (ServiceException e) {
			request.getRequestDispatcher(ControllerConstant.BASE_LAYOUT_PAGE_URL).forward(request, response);
			logger.log(Level.ERROR, e);
		}

	}

}
