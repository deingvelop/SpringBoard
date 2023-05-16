package com.example.springboard.preference.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.springboard.preference.vo.PreferenceVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PreferenceDAOImpl implements PreferenceDAO {
	private final SqlSession session;

	@Override
	public PreferenceVO isPresent(PreferenceVO requestVO) {
		String statement = "preferenceMapper.findByPostIdAndMemberId";
		PreferenceVO result = session.selectOne(statement, requestVO);
		return result;
	}

	@Override
	public int createLike(PreferenceVO requestVO) {
		String statement = "preferenceMapper.insertLike";
		int result = session.selectOne(statement, requestVO);
		return result;
	}

	@Override
	public int createDislike(PreferenceVO requestVO) {
		String statement = "preferenceMapper.insertDislike";
		int result = session.selectOne(statement, requestVO);
		return result;
	}
	
	@Override
	public int deletePreference(PreferenceVO presentVO) {
		String statement = "preferenceMapper.deletePreference";
		int result = session.delete(statement, presentVO);
		return result;
	}


}
