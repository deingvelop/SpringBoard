package com.example.springboard.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboard.comment.dao.CommentDAO;
import com.example.springboard.comment.vo.CommentVO;
import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.post.vo.PostVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentDAO commentDAO;

	@Override
	public List<CommentVO> loadPostComments(int postId) {
		List<CommentVO> result = commentDAO.loadPostComments(postId);
		return result;
	}
	
	@Override
	public void createComment(CommentVO requestVO) {
		
		int result = commentDAO.createComment(requestVO);
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void updateComment(CommentVO requestVO) {
		
		int result = commentDAO.updateComment(requestVO);
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteComment(int commentId) {
		int result = commentDAO.deleteComment(commentId);
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}
}
