package com.example.springboard.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.member.dao.MemberDAO;
import com.example.springboard.member.vo.LoginRequestVO;
import com.example.springboard.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO memberDAO;
	
	@Override
	public MemberVO login(LoginRequestVO requestVO) {
		
		log.info(memberDAO);
		MemberVO loginMember = memberDAO.login(requestVO).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		return loginMember;
		
	}
	
}
