package by.htp.ex.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idNews = 0;
	private String title = "";
	private String briefNews = "";
	private String content = "";
	private LocalDate newsDate = null;

	public News() {
	}

	public News(int idNews, String title, String briefNews, String content, LocalDate newsDate) {
		super();
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
	}

	public News(String title, String briefNews, String content, LocalDate newsDate) {
		super();
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
	}

	public Integer getIdNews() {
		return idNews;
	}

	public void setIdNews(Integer idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefNews() {
		return briefNews;
	}

	public void setBriefNews(String briefNews) {
		this.briefNews = briefNews;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;

	}

	public LocalDate getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(LocalDate newsDate) {
		this.newsDate = newsDate;
	}

}
