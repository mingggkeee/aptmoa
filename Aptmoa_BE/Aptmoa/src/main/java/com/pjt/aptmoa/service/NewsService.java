package com.pjt.aptmoa.service;

import java.util.List;

import com.pjt.aptmoa.dto.News;

public interface NewsService {
	public List<News> retrieveNews();
	public News detailNews(int newsno);
	public boolean writeNews(News news);
	public boolean deleteNews(int newsno);
	public List<News> crawlNews(String regtime);
}
