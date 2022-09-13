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

public class UserDAO implements IUserDAO {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final String SQL_USER_INSERT = "INSERT INTO user (login, password, email, access) VALUES (?,?,?,?)";
	private static final String SQL_USER_SELECT_BY_LOGIN_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";
	private static final String SQL_USER_SELECT_BY_LOGIN = "SELECT * FROM user WHERE login=?";
	private static final String SQL_USER_SELECT_BY_EMAIL = "SELECT * FROM user WHERE email=?";
	private static final String ACCESS_FIELD_NAME = "access";

	@Override
	public boolean logination(User user) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_USER_SELECT_BY_LOGIN_PASSWORD)) {

			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			final ResultSet resultSet = stmt.executeQuery();
			return resultSet.next();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public String getRole(User user) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_USER_SELECT_BY_LOGIN_PASSWORD)) {

			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			final ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			String accesLevel = resultSet.getString(ACCESS_FIELD_NAME);
			return accesLevel;

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean registration(User user) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_USER_INSERT)) {
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
				PreparedStatement stmt = connection.prepareStatement(SQL_USER_SELECT_BY_LOGIN)) {
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
				PreparedStatement stmt = connection.prepareStatement(SQL_USER_SELECT_BY_EMAIL)) {
			stmt.setString(1, email);
			final ResultSet resultSet = stmt.executeQuery();
			return resultSet.next();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}
}
