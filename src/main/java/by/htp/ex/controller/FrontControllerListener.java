package by.htp.ex.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.htp.ex.dao.connection.ConnectionPool;

@WebListener
public class FrontControllerListener implements ServletContextListener {

	public FrontControllerListener() {

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPool.getInstance().dispose();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ConnectionPool.getInstance();
	}

}
