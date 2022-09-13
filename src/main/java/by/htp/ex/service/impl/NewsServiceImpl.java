package by.htp.ex.service.impl;

import java.util.List;
import java.util.Map;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.NewsValidation;
import by.htp.ex.util.validation.ValidationProvider;

public class NewsServiceImpl implements INewsService {

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	private final NewsValidation newsValidation = ValidationProvider.getInstance().getNewsValidation();

	@Override
	public boolean add(News news) throws ServiceException {
		try {
			return newsDAO.addNews(news);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean save(int id, News news) throws ServiceException {
		try {
			return newsDAO.saveNews(id, news);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
		try {
			return newsDAO.getLatestsList(count);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		try {
			return newsDAO.delete(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteAllById(String[] idStringArr) throws ServiceException {
		try {
			for (int i = 0; i < idStringArr.length; i++) {
				int id = Integer.parseInt(idStringArr[i]);
				newsDAO.delete(id);
			}
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isNewsDataValid(String title, String brief, String content) {
		return newsValidation.checkNewsData(title, brief, content);
	}

	@Override
	public Map<String, String> getInvalidNewsData(String title, String brief, String content) throws ServiceException {
		return newsValidation.getInvalidNewsData();
	}

}
