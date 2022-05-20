package com.pjt.aptmoa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.aptmoa.dto.Comment;

@Mapper
public interface CommentMapper {

	public List<Comment> selectComment(int qnano);
	public int insertComment(Comment comment);
	public int deleteComment(int commentno);
	
}
