package com.example.springboard.post.dao;

import java.util.List;

import com.example.springboard.post.vo.PostVO;

public interface PostDAO {

	int createPost(PostVO requestVO);

	List<PostVO> loadPosts();

	PostVO showPost(int postId);

	int updatePost(PostVO postVO);

	int deletePost(int postId);

}
