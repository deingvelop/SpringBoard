package com.example.springboard.post.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboard.post.vo.PostVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class PostDAOImpl implements PostDAO {
	
	@Override
	public List<PostVO> loadPosts() {
		
		String statement = "postMapper.showPosts";
		List<PostVO> result = session.selectList(statement);
		return result;
	}
	
	@Autowired
	private SqlSession session;

	@Override
	public int createPost(PostVO requestVO) {
		log.info("request=" + requestVO);
		
		String statement = "postMapper.insertPost";
	    int result = session.insert(statement, requestVO);

	    log.info("result=" + result);
		
	    return result;
	}

	@Override
	public PostVO showPost(int postId) {
		log.info("request=" + postId);
		
		String statement = "postMapper.findById";
	    PostVO result = session.selectOne(statement, postId);

	    log.info("result=" + result);
		
	    return result;
	}

	@Override
	public int updatePost(PostVO postVO) {
		log.info("request=" + postVO);
		
		String statement = "postMapper.updatePost";
	    int result = session.update(statement, postVO);

	    log.info("result=" + result);
		
	    return result;
	}

	@Override
	public int deletePost(int postId) {
		
		String statement = "postMapper.deletePost";
	    int result = session.delete(statement, postId);

	    log.info("result=" + result);
		return result;
	}

}
