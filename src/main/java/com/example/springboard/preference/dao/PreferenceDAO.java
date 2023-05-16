package com.example.springboard.preference.dao;

import com.example.springboard.preference.vo.PreferenceVO;

public interface PreferenceDAO {
	PreferenceVO isPresent(PreferenceVO requestVO);
	
	int createLike(PreferenceVO requestVO);

	int createDislike(PreferenceVO requestVO);

	int deletePreference(PreferenceVO presentVO);

}
