package com.pjt.aptmoa.service;

import java.util.List;

import com.pjt.aptmoa.dto.Qna;

public interface QnaService {
	public List<Qna> retrieveQna();
	public Qna detailQna(int qnano);
	public void updateHit(int qnano);
	public boolean writeQna(Qna qna);
	public boolean updateQna(Qna qna);
	public boolean deleteQna(int qnano);
}
