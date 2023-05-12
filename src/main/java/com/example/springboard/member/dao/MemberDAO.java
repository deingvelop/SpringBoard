package com.example.springboard.member.dao;

import java.util.Optional;

import com.example.springboard.member.vo.MemberRequestVO;
import com.example.springboard.member.vo.MemberVO;

public interface MemberDAO {

	Optional<MemberVO> login(MemberRequestVO requestVO);

	int join(MemberRequestVO requestVO);

}
