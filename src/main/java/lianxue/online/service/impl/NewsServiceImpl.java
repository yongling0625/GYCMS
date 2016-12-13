package lianxue.online.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lianxue.online.mapper.NewsMapper;
import lianxue.online.model.News;
import lianxue.online.service.NewsService;
@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsMapper newsMapper;

	@Override
	public List<News> selectNewsList() {
		return newsMapper.selectAll();
	}

	@Override
	public void addNews(News news) {
		news.setCreateTime(new Date());
		newsMapper.insertSelective(news);
	}

	@Override
	public News selectNews(Long id) {
		return newsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void editNews(News news) {
		newsMapper.updateByPrimaryKeySelective(news);
	}

	@Override
	public void deleteNews(Long id) {
		newsMapper.deleteByPrimaryKey(id);
	}

}
