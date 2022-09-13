package by.htp.ex.service;

import java.util.List;
import java.util.Map;

import by.htp.ex.bean.News;

public interface INewsService {
	boolean add(News news) throws ServiceException;

	List<News> latestList(int count) throws ServiceException;

	List<News> list() throws ServiceException;

	News findById(int id) throws ServiceException;

	boolean save(int id, News news) throws ServiceException;

	boolean delete(int id) throws ServiceException;

	void deleteAllById(String[] idStringArr) throws ServiceException;

	boolean isNewsDataValid(String title, String brief, String content);

	Map<String, String> getInvalidNewsData(String title, String brief, String content) throws ServiceException;
}
