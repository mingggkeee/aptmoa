package com.pjt.aptmoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.aptmoa.dto.Comment;
import com.pjt.aptmoa.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper cMapper;
	
	@Override
	public List<Comment> retrieveComment(int qnano) {
		return cMapper.selectComment(qnano);
	}

	@Override
	public boolean writeComment(Comment comment) {
		return cMapper.insertComment(comment) == 1;
	}

	@Override
	public boolean deleteComment(int commentno) {
		return cMapper.deleteComment(commentno) == 1;
	}

}
