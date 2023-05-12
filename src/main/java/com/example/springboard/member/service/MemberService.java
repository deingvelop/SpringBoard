package com.example.springboard.member.service;

import com.example.springboard.member.vo.LoginRequestVO;
import com.example.springboard.member.vo.MemberVO;

public interface MemberService {

	MemberVO login(LoginRequestVO requestVO);

}
