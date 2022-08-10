package by.htp.ex.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connection.ConnectionPool;
import by.htp.ex.dao.connection.ConnectionPoolException;
import by.htp.ex.util.Constant;

public class UserDAO implements IUserDAO {
	ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean logination(String login, String password) throws DaoException {
		try (PreparedStatement stmt = connectionPool.takeConnection()
				.prepareStatement(Constant.SQL_USER_SELECT_BY_LOGIN_PASSWORD)) {
			stmt.setString(1, login);
			stmt.setString(2, password);
			final ResultSet resultSet = stmt.executeQuery();
			resultSet.next();

			String accessLevel = resultSet.getString("access");

			return accessLevel.equalsIgnoreCase("admin");

		} catch (SQLException e) {
			return false;
		} catch (ConnectionPoolException e1) {
			return false;
		}

	}

	@Override
	public String getRole(String login, String password) {
		return "user";
	}

	@Override
	public boolean registration(User user) throws DaoException {
		try (PreparedStatement stmt = connectionPool.takeConnection().prepareStatement(Constant.SQL_USER_INSERT)) {
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getAccess());

			return stmt.execute();

		} catch (SQLException e) {
			return false;
		} catch (ConnectionPoolException e1) {
			return false;
		}
	}

}
