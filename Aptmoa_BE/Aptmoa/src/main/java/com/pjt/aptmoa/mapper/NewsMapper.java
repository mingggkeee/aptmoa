package com.pjt.aptmoa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.aptmoa.dto.News;

@Mapper
public interface NewsMapper {

	public List<News> selectNews(String regtime);
	public News selectNewsByNo(int newsno);
	public int insertNews(News news);
	public int deleteNews(int newsno);
	
}
