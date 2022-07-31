package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider provider = ServiceProvider.getInstance();
		IUserService userService = provider.getUserService();
		
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");;
		String confirm_password = request.getParameter("confirm_password");;
		String email = request.getParameter("email");
		
		User user = new User(login, password, confirm_password, email);
		
		try {
			if (!userService.registration(user)) {
				List<String> invalidRegistrationData = userService.getInvalidRegistrationData();
				request.setAttribute("invalidRegistrationData", invalidRegistrationData);
				System.out.println("her tam plaval");
				
			}
			System.out.println("pampampam");
			request.setAttribute("registration_status", true);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			//do something
		}
		
		
		
	}

}
