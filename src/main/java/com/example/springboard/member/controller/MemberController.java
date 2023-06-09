package com.example.springboard.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.springboard.member.service.MemberService;
import com.example.springboard.member.vo.MemberRequestVO;
import com.example.springboard.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
//@SessionAttributes("loginMember")
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/login")
	public String goToLoginPage() {
		return "login";
	}
	
	@PostMapping("login")
	public String login(MemberRequestVO requestVO, @ModelAttribute("loginMember") MemberVO loginMember, HttpSession session) {
		loginMember = memberService.login(requestVO);
	    session.setAttribute("loginMember", loginMember);
	    log.info("session에 저장된 값=" + session.getAttribute("loginMember"));
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
