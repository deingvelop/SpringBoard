package com.example.springboard.member.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.springboard.member.vo.LoginRequestVO;
import com.example.springboard.member.vo.MemberVO;

public interface MemberDAO {

	Optional<MemberVO> login(LoginRequestVO requestVO);

}
