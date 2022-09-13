package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute(ControllerConstant.JSP_REGISTRATION_STATUS_ATT,
				ControllerConstant.JSP_REGISTRATION_STATUS_ATT_IN_PROGRESS);
		request.getSession().setAttribute(ControllerConstant.JSP_AUTHENTICATION_ERROR, null);
		request.getSession().setAttribute(ControllerConstant.JSP_INVALID_REGISTRATION_DATA_ATT, null);
		request.getRequestDispatcher(ControllerConstant.BASE_LAYOUT_PAGE_URL).forward(request, response);
	}

}
