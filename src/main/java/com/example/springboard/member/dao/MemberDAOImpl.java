package com.example.springboard.member.dao;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboard.member.vo.LoginRequestVO;
import com.example.springboard.member.vo.MemberVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession session;

	@Override
	public Optional<MemberVO> login(LoginRequestVO requestVO) {
		log.info("request=" + requestVO);
		 
		String statement = "memberMapper.findByUsernameAndPassword";
		MemberVO result = session.selectOne(statement, requestVO);
	    Optional<MemberVO> loginMember = Optional.ofNullable(result);

		log.info("result=" + loginMember);

	    return loginMember;
	}

}
