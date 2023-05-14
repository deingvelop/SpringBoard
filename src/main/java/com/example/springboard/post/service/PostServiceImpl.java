package com.example.springboard.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.post.dao.PostDAO;
import com.example.springboard.post.vo.PostVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostDAO postDAO;

	@Override
	public List<PostVO> loadPosts() {
		List<PostVO> result = postDAO.loadPosts();
		return result;
	}
	
	@Override
	public int createPost(PostVO requestVO) {
		
		int result = postDAO.createPost(requestVO);
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public PostVO showPost(int postId) {
		PostVO result = postDAO.showPost(postId);
		return result;
	}

	@Override
	public PostVO updatePost(PostVO postVO) {
		
		int result = postDAO.updatePost(postVO);
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		
		PostVO updatedPost = postDAO.showPost(postVO.getId());
		return updatedPost;
	}

	@Override
	public void deletePost(int postId) {
		int result = postDAO.deletePost(postId);
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}
	
}
