package com.example.springboard.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	@GetMapping("/login")
	public String goToLogin() {
		return "login";
	}
	
}
