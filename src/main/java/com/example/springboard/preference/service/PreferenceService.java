package com.example.springboard.preference.service;

import com.example.springboard.preference.vo.PreferenceVO;

public interface PreferenceService {
	PreferenceVO findByVO(PreferenceVO requestVO);
	
	void createLike(PreferenceVO requestVO);

	void createDislike(PreferenceVO requestVO);

	void deleteLike(PreferenceVO requestVO);

	void deleteDislike(PreferenceVO requestVO);

}
