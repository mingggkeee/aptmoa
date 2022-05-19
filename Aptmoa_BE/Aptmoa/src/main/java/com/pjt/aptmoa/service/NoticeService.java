package com.pjt.aptmoa.service;

import java.util.List;

import com.pjt.aptmoa.dto.Notice;

public interface NoticeService {
	public List<Notice> retrieveNotice();
	public Notice detailNotice(int noticeno);
	public void updateHit(int noticeno);
	public boolean writeNotice(Notice notice);
	public boolean updateNotice(Notice notice);
	public boolean deleteNotice(int noticeno);
}
