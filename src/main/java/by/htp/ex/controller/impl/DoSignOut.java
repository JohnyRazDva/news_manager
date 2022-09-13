package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute(ControllerConstant.JSP_USER_ATT, ControllerConstant.JSP_USER_ATT_NOT_ACTIVE);
		response.sendRedirect(ControllerConstant.REDIRECT_TO_BASE_PAGE_URL);
	}

}
