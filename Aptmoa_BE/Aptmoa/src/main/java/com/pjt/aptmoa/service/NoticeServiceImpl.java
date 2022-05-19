package com.pjt.aptmoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.aptmoa.dto.Notice;
import com.pjt.aptmoa.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	
    @Autowired
	private NoticeMapper noticeMapper;

    @Override
	public List<Notice> retrieveNotice() {
		return noticeMapper.selectNotice();
	}
    
  	@Override
	public boolean writeNotice(Notice notice) {
		return noticeMapper.insertNotice(notice) == 1;
	}

	@Override
	public Notice detailNotice(int noticeno) {
		return noticeMapper.selectNoticeByNo(noticeno);
	}
	
	@Override
	public void updateHit(int noticeno) {
		noticeMapper.updateHit(noticeno);
	}

	@Override
	@Transactional
	public boolean updateNotice(Notice notice) {
		return noticeMapper.updateNotice(notice) == 1;
	}

	@Override
	@Transactional
	public boolean deleteNotice(int noticeno) {
		return noticeMapper.deleteNotice(noticeno) == 1;
	}
}