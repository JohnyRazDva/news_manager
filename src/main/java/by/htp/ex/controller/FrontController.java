package by.htp.ex.controller;

import java.io.IOException;

import by.htp.ex.dao.connection.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = new CommandProvider();

	public FrontController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		ConnectionPool.getInstance();
		super.init();
	}

	@Override
	public void destroy() {
		ConnectionPool.getInstance().dispose();
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter("command");

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
