package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute("registration_status", "in_progress");
		request.getSession().setAttribute("invalidRegistrationData", null);
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
	}

}
