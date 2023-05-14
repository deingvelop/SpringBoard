package com.example.springboard.comment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springboard.comment.service.CommentService;
import com.example.springboard.comment.vo.CommentVO;
import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{postId}")
public class CommentController {
	private final CommentService commentService;
	
	@ModelAttribute("comment")
	public CommentVO createCommentVO() {
	    return new CommentVO();
	}

//	@GetMapping("/comments")
//	public String showPostComments(@PathVariable("postId") int postId, @ModelAttribute("comments") List<CommentVO> comments) {
//		
//		comments = commentService.loadPostComments(postId);
//		return "redirect:/post/" + postId;
//	}
	
	@PostMapping("comment")
	public String createPost(@PathVariable("postId") int postId, CommentVO requestVO, HttpSession session) {
		
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

		requestVO.setPostId(postId);
		requestVO.setMemberId(loginMember.getId());
		requestVO.setMemberNickname(loginMember.getNickname());
	
		commentService.createComment(requestVO);
		
		return "redirect:/post/" + postId;
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

	@PostMapping(value = "comment/{commentId}")
	public String updateComment(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId, @ModelAttribute("comment") CommentVO requestVO, HttpSession session) {
		log.info("수정하려고 들어온 데이터=" + requestVO);
		
		requestVO.setId(commentId);
		commentService.updateComment(requestVO);
		
		log.info("수정 controller 메서드 수행 완료");
		
		return "redirect:/post/" + postId;
	}
	
	@DeleteMapping(value = "comment/{commentId}")
	public String deleteComment(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId, HttpSession session) {
		
		commentService.deleteComment(commentId);
				
		return "redirect:/post/" + postId;
	}
}
