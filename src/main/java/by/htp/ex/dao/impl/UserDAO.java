package by.htp.ex.dao.impl;

import java.sql.Connection;
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
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(Constant.SQL_USER_SELECT_BY_LOGIN_PASSWORD)) {

			stmt.setString(1, login);
			stmt.setString(2, password);
			final ResultSet resultSet = stmt.executeQuery();
			return resultSet.next();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public String getRole(String login, String password) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(Constant.SQL_USER_SELECT_BY_LOGIN_PASSWORD)) {

			stmt.setString(1, login);
			stmt.setString(2, password);
			final ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			String accesLevel = resultSet.getString("access");
			return accesLevel;

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean registration(User user) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(Constant.SQL_USER_INSERT)) {
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getAccess());

			stmt.execute();
			return true;

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean isUserExist(String login) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(Constant.SQL_USER_SELECT_BY_LOGIN)) {
			stmt.setString(1, login);
			final ResultSet resultSet = stmt.executeQuery();
			return resultSet.next();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean isEmailInUse(String email) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(Constant.SQL_USER_SELECT_BY_EMAIL)) {
			stmt.setString(1, email);
			final ResultSet resultSet = stmt.executeQuery();
			return resultSet.next();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}
}
