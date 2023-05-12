package com.example.springboard.member.service;

import org.springframework.stereotype.Service;

import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.member.dao.MemberDAO;
import com.example.springboard.member.vo.MemberRequestVO;
import com.example.springboard.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO memberDAO;
	
	@Override
	public MemberVO login(MemberRequestVO requestVO) {
		
		log.info(memberDAO);
		MemberVO loginMember = memberDAO.login(requestVO).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		return loginMember;
		
	}

	@Override
	public MemberRequestVO join(MemberRequestVO requestVO) {
		
		int result = memberDAO.join(requestVO);
		
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		return requestVO;
	}
	
}
