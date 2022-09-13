package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.News;

public interface INewsDAO {
	List<News> getList() throws NewsDAOException;

	List<News> getLatestsList(int count) throws NewsDAOException;

	News fetchById(int id) throws NewsDAOException;

	boolean addNews(News news) throws NewsDAOException;

	boolean saveNews(int id, News news) throws NewsDAOException;

	boolean delete(int id) throws NewsDAOException;
}
