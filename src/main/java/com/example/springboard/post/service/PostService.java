package com.example.springboard.post.service;

import java.util.List;

import com.example.springboard.post.vo.PostVO;

public interface PostService {

	int createPost(PostVO requestVO);

	List<PostVO> loadPosts();

	PostVO showPost(int postId);

	PostVO updatePost(PostVO postVO);
	
}
