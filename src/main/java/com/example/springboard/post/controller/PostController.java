package com.example.springboard.post.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.member.vo.MemberVO;
import com.example.springboard.post.service.PostService;
import com.example.springboard.post.vo.PostVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@GetMapping("board")
	public String showBoard(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			throw new BusinessException(ErrorCode.INVALID_LOGIN);
		}
		
		List<PostVO> posts = postService.loadPosts();
		session.setAttribute("posts", posts);
		return "board";
	}
	
	@GetMapping("post/new")
	public String goToNewPostPage(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			throw new BusinessException(ErrorCode.INVALID_LOGIN);
		}
		return "newpost";
	}
	
	@PostMapping("post/new")
	public String createPost(PostVO requestVO, HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			throw new BusinessException(ErrorCode.INVALID_LOGIN);
		}
		requestVO.setMemberId(loginMember.getId());
		requestVO.setMemberNickname(loginMember.getNickname());
	
		postService.createPost(requestVO);
		
		return "redirect:/board";
	}
	
	@GetMapping("post/{postId}")
	public String goToPost(@PathVariable("postId") int postId, HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			throw new BusinessException(ErrorCode.INVALID_LOGIN);
		}
		
		PostVO postVO = postService.showPost(postId);
		session.setAttribute("post", postVO);
		return "post";
	}
	
}
