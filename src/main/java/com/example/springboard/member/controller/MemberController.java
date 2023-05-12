package com.example.springboard.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboard.member.service.MemberService;
import com.example.springboard.member.vo.MemberRequestVO;
import com.example.springboard.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/login")
	public String goToLoginPage() {
		return "login";
	}
	
	@PostMapping("login")
	public String login(MemberRequestVO requestVO, HttpSession session) {
		MemberVO loginMemgber = memberService.login(requestVO);
		session.setAttribute("loginMember", loginMemgber);
		return "redirect:/board";
	}
	
	@PostMapping("logout")
	public String logout(HttpSession session) {
	    session.removeAttribute("loginMember");
	    return "redirect:/login";
	}
	
	@GetMapping("join")
	public String goToJoinPage() {
		return "join";
	}
	
	@PostMapping("join")
	public String join(MemberRequestVO requestVO) {
		MemberRequestVO newMember = memberService.join(requestVO);
		return "redirect:/login";
	}
}
