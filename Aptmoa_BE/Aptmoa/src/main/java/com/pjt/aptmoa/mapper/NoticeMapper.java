package com.pjt.aptmoa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.aptmoa.dto.Notice;
@Mapper
public interface NoticeMapper {
	public List<Notice> selectNotice();
	public Notice selectNoticeByNo(int noticeno);
	public void updateHit(int articleno);
	public int insertNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(int noticeno);
}