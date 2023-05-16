package com.example.springboard.preference.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;
import com.example.springboard.preference.dao.PreferenceDAO;
import com.example.springboard.preference.vo.PreferenceVO;
import com.example.springboard.preference.vo.PreferenceVO.PreferenceType;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {
	private final PreferenceDAO preferenceDAO;

	@Override
	public PreferenceVO findByVO(PreferenceVO requestVO) {
		PreferenceVO result = preferenceDAO.isPresent(requestVO);
		return result;
	}

	@Override
	public void createLike(PreferenceVO requestVO) {
		PreferenceVO presentVO = findByVO(requestVO);
		
		// 만약 해당 유저가 이미 이 게시글에 반응을 했으면 예외 처리
		if (presentVO != null) {
			if (presentVO.getType() == PreferenceType.DISLIKE) {
				throw new BusinessException(ErrorCode.DUPLICATE_PREFERENCE_DISLIKE);
			} else if (presentVO.getType() == PreferenceType.LIKE) {
				throw new BusinessException(ErrorCode.ALREADY_LIKED);
			}
		}
		
		int result = preferenceDAO.createLike(requestVO);
		
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void createDislike(PreferenceVO requestVO) {
		PreferenceVO presentVO = findByVO(requestVO);
		
		// 만약 해당 유저가 이미 이 게시글에 반응을 했으면 예외 처리
		if (presentVO != null) {
			if (presentVO.getType() == PreferenceType.LIKE) {
				throw new BusinessException(ErrorCode.DUPLICATE_PREFERENCE_LIKE);
			} else if (presentVO.getType() == PreferenceType.DISLIKE) {
				throw new BusinessException(ErrorCode.ALREADY_DISLIKED);
			}
		}
		
		int result = preferenceDAO.createDislike(requestVO);
		
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public void deleteLike(PreferenceVO requestVO) {
		PreferenceVO presentVO = findByVO(requestVO);
		if (presentVO == null) {
			throw new BusinessException(ErrorCode.LIKE_NOT_FOUND);
		}
		
		int result = preferenceDAO.deletePreference(presentVO);
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteDislike(PreferenceVO requestVO) {
		PreferenceVO presentVO = findByVO(requestVO);
		if (presentVO == null) {
			throw new BusinessException(ErrorCode.DISLIKE_NOT_FOUND);
		}
		
		int result = preferenceDAO.deletePreference(presentVO);
		if (result != 1) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

}
