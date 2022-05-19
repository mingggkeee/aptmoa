package com.pjt.aptmoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pjt.aptmoa.dto.Qna;
import com.pjt.aptmoa.mapper.QnaMapper;

public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	public List<Qna> retrieveQna() {
		return qnaMapper.selectQna();
	}

	@Override
	public Qna detailQna(int qnano) {
		return qnaMapper.selectQnaByNo(qnano);
	}

	@Override
	public void updateHit(int qnano) {
		qnaMapper.updateHit(qnano);
	}

	@Override
	public boolean writeQna(Qna qna) {
		return qnaMapper.insertQna(qna) == 1;
	}

	@Override
	public boolean updateQna(Qna qna) {
		return qnaMapper.updateQna(qna) == 1;
	}

	@Override
	public boolean deleteQna(int qnano) {
		return qnaMapper.deleteQna(qnano) == 1;
	}

}
