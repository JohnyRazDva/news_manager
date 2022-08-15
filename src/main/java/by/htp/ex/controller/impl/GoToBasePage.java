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

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	private final static Logger logger = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> latestNews;
		try {
			latestNews = newsService.latestList(5);
			request.setAttribute("news", latestNews);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
		}

	}

}
