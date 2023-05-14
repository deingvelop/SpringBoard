package com.example.springboard.comment.service;

import java.util.List;

import com.example.springboard.comment.vo.CommentVO;

public interface CommentService {

	List<CommentVO> loadPostComments(int postId);

	void createComment(CommentVO requestVO);

	void updateComment(CommentVO requestVO);

	void deleteComment(int commentId);

}
