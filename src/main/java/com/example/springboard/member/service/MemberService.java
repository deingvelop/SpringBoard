package com.example.springboard.member.service;

import com.example.springboard.member.vo.MemberRequestVO;
import com.example.springboard.member.vo.MemberVO;

public interface MemberService {

	MemberVO login(MemberRequestVO requestVO);

	MemberRequestVO join(MemberRequestVO requestVO);

}
