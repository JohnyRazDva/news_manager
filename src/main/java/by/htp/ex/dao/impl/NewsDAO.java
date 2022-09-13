package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connection.ConnectionPool;
import by.htp.ex.dao.connection.ConnectionPoolException;

public class NewsDAO implements INewsDAO {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final static String SQL_GET_ALL_NEWS = "SELECT * FROM news";
	private final static String SQL_GET_LATEST_NEWS = "SELECT * FROM news ORDER BY id DESC LIMIT ?";
	private final static String SQL_INSERT_NEWS = "INSERT INTO news (title, brief, content, date) VALUES (?,?,?,?)";
	private final static String SQL_GET_NEWS_BY_ID = "SELECT * FROM news WHERE id=?";
	private final static String SQL_UPDATE_NEWS_BY_ID = "UPDATE news SET title=? , brief=? ,content=? WHERE id=?";
	private final static String SQL_DELETE_NEWS_BY_ID = "DELETE FROM news WHERE id=?";
	private final static String ID_FIELD_NAME = "id";
	private final static String TITLE_FIELD_NAME = "title";
	private final static String BRIEF_FIELD_NAME = "brief";
	private final static String CONTENT_FIELD_NAME = "content";
	private final static String DATE_FIELD_NAME = "date";

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_GET_LATEST_NEWS)) {
			stmt.setInt(1, count);
			final ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(ID_FIELD_NAME);
				String title = resultSet.getString(TITLE_FIELD_NAME);
				String brief = resultSet.getString(BRIEF_FIELD_NAME);
				String content = resultSet.getString(CONTENT_FIELD_NAME);
				LocalDate date = resultSet.getTimestamp(DATE_FIELD_NAME).toLocalDateTime().toLocalDate();
				News news = new News(id, title, brief, content, date);
				result.add(news);
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
		return result;
	}

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> result = new ArrayList<News>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_GET_ALL_NEWS)) {
			final ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(ID_FIELD_NAME);
				String title = resultSet.getString(TITLE_FIELD_NAME);
				String brief = resultSet.getString(BRIEF_FIELD_NAME);
				String content = resultSet.getString(CONTENT_FIELD_NAME);
				LocalDate date = resultSet.getTimestamp(DATE_FIELD_NAME).toLocalDateTime().toLocalDate();
				News news = new News(id, title, brief, content, date);
				result.add(news);
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}

		return result;
	}

	@Override
	public News fetchById(int id) throws NewsDAOException {
		News news = new News();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_GET_NEWS_BY_ID)) {
			stmt.setInt(1, id);
			final ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				String title = resultSet.getString(TITLE_FIELD_NAME);
				String brief = resultSet.getString(BRIEF_FIELD_NAME);
				String content = resultSet.getString(CONTENT_FIELD_NAME);
				LocalDate date = resultSet.getTimestamp(DATE_FIELD_NAME).toLocalDateTime().toLocalDate();
				news = new News(id, title, brief, content, date);
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
		return news;
	}

	@Override
	public boolean addNews(News news) throws NewsDAOException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_NEWS)) {
			stmt.setString(1, news.getTitle());
			stmt.setString(2, news.getBriefNews());
			stmt.setString(3, news.getContent());
			stmt.setDate(4, Date.valueOf(news.getNewsDate()));
			stmt.execute();
			return true;

		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
	}

	@Override
	public boolean saveNews(int id, News news) throws NewsDAOException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_NEWS_BY_ID)) {
			stmt.setString(1, news.getTitle());
			stmt.setString(2, news.getBriefNews());
			stmt.setString(3, news.getContent());
			stmt.setInt(4, id);
			stmt.execute();
			return true;

		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
	}

	@Override
	public boolean delete(int id) throws NewsDAOException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_DELETE_NEWS_BY_ID)) {
			stmt.setInt(1, id);
			stmt.execute();
			return true;

		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
	}

}
