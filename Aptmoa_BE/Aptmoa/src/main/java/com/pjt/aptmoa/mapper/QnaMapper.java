package com.pjt.aptmoa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.aptmoa.dto.Comment;
import com.pjt.aptmoa.dto.Qna;
@Mapper
public interface QnaMapper {
	public List<Qna> selectQna();
	public Qna selectQnaByNo(int qnano);
	public void updateHit(int qnano);
	public int insertQna(Qna qna);
	public int updateQna(Qna qna);
	public int deleteQna(int qnano);
}
