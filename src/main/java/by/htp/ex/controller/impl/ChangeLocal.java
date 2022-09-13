package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocal implements Command {
	private final static String JSP_LOCALE_PARAM = "local";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession(true).setAttribute(JSP_LOCALE_PARAM, request.getParameter(JSP_LOCALE_PARAM));
		response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
	}

}
