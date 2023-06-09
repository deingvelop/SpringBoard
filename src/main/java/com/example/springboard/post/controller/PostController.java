package com.example.springboard.post.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.springboard.comment.service.CommentService;
import com.example.springboard.comment.vo.CommentVO;
import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.member.vo.MemberVO;
import com.example.springboard.post.service.PostService;
import com.example.springboard.post.vo.PostVO;
import com.example.springboard.preference.service.PreferenceService;
import com.example.springboard.preference.vo.PreferenceVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	private final CommentService commentService;
	private final PreferenceService preferenceService;
	
	@ModelAttribute("post")
	public PostVO createPostVO() {
	    return new PostVO();
	}
	
	@ModelAttribute("posts")
	public List<PostVO> createPostVOs() {
		return new ArrayList<PostVO>();
	}
	
	@ModelAttribute("comments")
	public List<CommentVO> createCommentVOs() {
		return new ArrayList<CommentVO>();
	}
	
	@ModelAttribute("preference")
	public PreferenceVO createPreference() {
		return new PreferenceVO();
	}

	@GetMapping("board")
	public String showBoard(@ModelAttribute("posts") List<PostVO> posts) {
		posts.addAll(postService.loadPosts());
		return "board";
	}
	
	@GetMapping("post/new")
	public String goToNewPostPage() {
		return "newpost";
	}
	
	@PostMapping("post/new")
	public String createPost(@ModelAttribute("post") PostVO requestVO, @SessionAttribute MemberVO loginMember) {
		requestVO.setMemberId(loginMember.getId());
		requestVO.setMemberNickname(loginMember.getNickname());
	
		postService.createPost(requestVO);
		
		return "redirect:/board";
	}
	
	@GetMapping("post/{postId}")
	public String goToPost(@PathVariable("postId") int postId, @SessionAttribute("loginMember") MemberVO loginMember, Model model) {
		log.info("post/" + postId + "호출 완료");
		
		PostVO postVO = postService.showPost(postId);
		List<CommentVO> comments = commentService.loadPostComments(postId);
		PreferenceVO preference = preferenceService.findByVO(new PreferenceVO(postId, loginMember.getId()));
		
		log.info(preference);
		
		model.addAttribute("post", postVO);
		model.addAttribute("comments", comments);
		model.addAttribute("preference", preference);
		
		log.info(model.getAttribute("preference"));
		return "post";
	}
		
	@GetMapping("post/{postId}/edit")
	public String goToEditPostPage(@PathVariable("postId") int postId, Model model) {
	    PostVO postVO = postService.showPost(postId);
	    model.addAttribute("post", postVO);
	    return "editpost";
	}
	
//	@PutMapping(value = "post/{postId}/edit")
//	@ResponseBody
//	public ResponseEntity<PostVO> editPost(@PathVariable("postId") int postId, @ModelAttribute("post") PostVO postVO, HttpSession session) {		
//		postVO.setId(postId);
//		PostVO result = postService.updatePost(postVO);
//		session.setAttribute("post", result); // 수정된 결과 객체를 세션에 저장		
//		return new ResponseEntity<PostVO>(result, HttpStatus.OK);
//	}
	
	@PostMapping(value = "post/{postId}/edit")
	public String editPost(@PathVariable("postId") int postId, @ModelAttribute("post") PostVO postVO, HttpSession session) {		
		log.info("수정할 데이터 =" + postVO);
		postVO.setId(postId);
		PostVO result = postService.updatePost(postVO);
		session.setAttribute("post", result); // 수정된 결과 객체를 세션에 저장		
		return "redirect:/post/" + postId;
	}

	@DeleteMapping(value = "post/{postId}")
	public String deletePost(@PathVariable("postId") int postId) {
		
		postService.deletePost(postId);
				
		return "redirect:/post" + postId;
	}
}