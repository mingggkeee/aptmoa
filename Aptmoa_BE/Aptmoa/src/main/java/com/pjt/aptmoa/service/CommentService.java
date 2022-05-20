package com.pjt.aptmoa.service;

import java.util.List;

import com.pjt.aptmoa.dto.Comment;

public interface CommentService {
	public List<Comment> retrieveComment(int qnano);
	public boolean writeComment(Comment comment);
	public boolean deleteComment(int commentno);
	
}
