package com.pjt.aptmoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.aptmoa.dto.News;
import com.pjt.aptmoa.mapper.NewsMapper;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public List<News> retrieveNews() {
		return newsMapper.selectNews();
	}

	@Override
	public News detailNews(int newsno) {
		return newsMapper.selectNewsByNo(newsno);
	}

	@Override
	public boolean writeNews(News news) {
		return newsMapper.insertNews(news) == 1;
	}

	@Override
	public boolean deleteNews(int newsno) {
		return newsMapper.deleteNews(newsno) == 1;
	}

}
