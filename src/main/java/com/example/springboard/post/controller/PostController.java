package com.example.springboard.post.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
public class PostController {
	
	@GetMapping("board")
	public String showBoard(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			throw new BusinessException(ErrorCode.INVALID_LOGIN);
		}
		return "board";
	}
}
