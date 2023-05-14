package com.example.springboard.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboard.comment.vo.CommentVO;
import com.example.springboard.post.vo.PostVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class CommentDAOImpl implements CommentDAO {

	private final SqlSession session;
	
	@Override
	public List<CommentVO> loadPostComments(int postId) {
		String statement = "commentMapper.findAllByPostId";
		List<CommentVO> result = session.selectList(statement, postId);
		return result;
	}
	
	@Override
	public int createComment(CommentVO requestVO) {
		log.info("request=" + requestVO);
		
		String statement = "commentMapper.insertComment";
	    int result = session.insert(statement, requestVO);

	    log.info("result=" + result);
		
	    return result;
	}

	@Override
	public int updateComment(CommentVO requestVO) {
		log.info("request=" + requestVO);
		
		String statement = "commentMapper.updateComment";
	    int result = session.update(statement, requestVO);

	    log.info("result=" + result);
		
	    return result;
	}

	@Override
	public int deleteComment(int commentId) {
		
		String statement = "commentMapper.deleteComment";
	    int result = session.delete(statement, commentId);

	    log.info("result=" + result);
		return result;
	}

}
