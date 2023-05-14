package com.example.springboard.post.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
//	@Autowired
	private final PostService postService;
	
	@ModelAttribute("post")
	public PostVO createPostVO() {
	    return new PostVO();
	}

	@GetMapping("board")
	public String showBoard(HttpSession session, @ModelAttribute("post") PostVO postVO) {
		
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			throw new BusinessException(ErrorCode.INVALID_LOGIN);
		}
		
		List<PostVO> posts = postService.loadPosts();
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
	public String goToPost(@PathVariable("postId") int postId, @ModelAttribute("post") PostVO postVO, HttpSession session) {
		log.info("post/" + postId + "호출 완료");	
		
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			throw new BusinessException(ErrorCode.INVALID_LOGIN);
		}
		
		postVO = postService.showPost(postId);
		return "post";
	}
		
	@GetMapping("post/{postId}/edit")
	public String goToEditPostPage(@PathVariable("postId") int postId, @ModelAttribute("post") PostVO postVO, HttpSession session) {
	    MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
	    if (loginMember == null) {
	        throw new BusinessException(ErrorCode.INVALID_LOGIN);
	    }
	    postVO = postService.showPost(postId);
	    return "editpost";
	}
	
	/*
	 * @PutMapping(value = "post/{postId}/edit")
	 * 
	 * @ResponseBody public ResponseEntity<PostVO> editPost(@PathVariable("postId")
	 * int postId, @RequestBody PostVO postVO, HttpSession session) {
	 * log.info("수정하려고 들어온 데이터=" + postVO);
	 * 
	 * MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
	 * log.warn(loginMember); if (loginMember == null) { throw new
	 * BusinessException(ErrorCode.INVALID_LOGIN); }
	 * 
	 * postVO.setId(postId); PostVO result = postService.updatePost(postVO);
	 * session.setAttribute("post", result); // 수정된 결과 객체를 세션에 저장
	 * 
	 * log.info("수정 controller 메서드 수행 완료");
	 * 
	 * return new ResponseEntity<PostVO>(result, HttpStatus.OK); }
	 */

	@PostMapping(value = "post/{postId}/edit")
	public String editPost(@PathVariable("postId") int postId, @ModelAttribute("post") PostVO postVO, HttpSession session) {
		log.info("수정하려고 들어온 데이터=" + postVO);
		
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		log.warn(loginMember);
		if (loginMember == null) {
			throw new BusinessException(ErrorCode.INVALID_LOGIN);
		}
		
		postVO.setId(postId);
		postVO = postService.updatePost(postVO);
		
		log.info("수정 controller 메서드 수행 완료");
		
		return "redirect:/post/" + postId;
	}
	
	@DeleteMapping(value = "post/{postId}")
	public String deletePost(@PathVariable("postId") int postId, HttpSession session) {
		
		postService.deletePost(postId);
				
		return "redirect:/board";
	}
}
