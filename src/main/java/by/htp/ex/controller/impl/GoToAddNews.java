package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(ControllerConstant.JSP_PRESENTATION_ATT,
				ControllerConstant.JSP_PRESENTATION_ATT_VALUE_ADD_NEWS);
		request.getSession().setAttribute(ControllerConstant.JSP_ADD_NEWS_SUCCESS_PARAM, null);
		request.getSession().setAttribute(ControllerConstant.JSP_INVALID_NEWS_DATA_ATT, null);
		request.getRequestDispatcher(ControllerConstant.BASE_LAYOUT_PAGE_URL).forward(request, response);
	}

}
