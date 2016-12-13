package lianxue.online.service;

import java.util.List;

import lianxue.online.model.News;


public interface NewsService {

	List<News> selectNewsList();

	void addNews(News news);

	News selectNews(Long id);

	void editNews(News news);

	void deleteNews(Long id);

}
