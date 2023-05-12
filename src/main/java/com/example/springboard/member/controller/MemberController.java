package com.example.springboard.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboard.member.service.MemberService;
import com.example.springboard.member.vo.LoginRequestVO;
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
	public String login(LoginRequestVO requestVO, HttpSession session) {
		MemberVO loginMemgber = memberService.login(requestVO);
		session.setAttribute("loginMember", loginMemgber);
		return "redirect:/board";
	}
	
	@PostMapping("logout")
	public String logout(HttpSession session) {
	    session.removeAttribute("loginMember");
	    return "redirect:/login";
	}
	
}
