package com.example.springboard.comment.dao;

import java.util.List;

import com.example.springboard.comment.vo.CommentVO;

public interface CommentDAO {

	List<CommentVO> loadPostComments(int postId);

	int createComment(CommentVO requestVO);

	int updateComment(CommentVO requestVO);

	int deleteComment(int commentId);

}
