package com.example.springboard.preference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.springboard.member.vo.MemberVO;
import com.example.springboard.preference.service.PreferenceService;
import com.example.springboard.preference.vo.PreferenceVO;
import com.example.springboard.preference.vo.PreferenceVO.PreferenceType;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{postId}")
public class PreferenceController {
	private final PreferenceService preferenceService;

	@ModelAttribute("preference")
	public PreferenceVO createPreference() {
		return new PreferenceVO();
	}
	
	@PostMapping("/like")
	public String createLike(@PathVariable("postId") int postId, @SessionAttribute("loginMember") MemberVO loginMember, @ModelAttribute("preference") PreferenceVO requestVO) {
		log.info("좋아요 요청");
		
		requestVO.setPostId(postId);
		requestVO.setMemberId(loginMember.getId());
		requestVO.setType(PreferenceType.LIKE);
		
		preferenceService.createLike(requestVO);
		
		return "redirect:/springboard/post/" + postId;
	}
	
	@PostMapping("/dislike")
	public String createDislike(@PathVariable("postId") int postId, @SessionAttribute("loginMember") MemberVO loginMember, @ModelAttribute("preference") PreferenceVO requestVO) {
		log.info("싫어요 요청");
		
		requestVO.setPostId(postId);
		requestVO.setMemberId(loginMember.getId());
		requestVO.setType(PreferenceType.DISLIKE);
		
		preferenceService.createDislike(requestVO);
		
		return "redirect:/springboard/post/" + postId;
	}
	
	@DeleteMapping("/like")
	public String deleteLike(@PathVariable("postId") int postId, @SessionAttribute("loginMember") MemberVO loginMember, @ModelAttribute("preference") PreferenceVO requestVO) {
		log.info("좋아요 취소 요청");
		
		requestVO.setPostId(postId);
		requestVO.setMemberId(loginMember.getId());
		requestVO.setType(PreferenceType.LIKE);
		preferenceService.deleteLike(requestVO);
		
		return "redirect:/springboard/post/" + postId;
	}
	
	@DeleteMapping("/dislike")
	public String deleteDislike(@PathVariable("postId") int postId, @SessionAttribute("loginMember") MemberVO loginMember, @ModelAttribute("preference") PreferenceVO requestVO) {
		log.info("싫어요 취소 요청");
		
		requestVO.setPostId(postId);
		requestVO.setMemberId(loginMember.getId());
		requestVO.setType(PreferenceType.DISLIKE);
		preferenceService.deleteDislike(requestVO);
		
		return "redirect:/springboard/post/" + postId;
	}
	
}
