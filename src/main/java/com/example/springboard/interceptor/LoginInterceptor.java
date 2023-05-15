package com.example.springboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboard.exception.BusinessException;
import com.example.springboard.exception.ErrorCode;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginMember = session.getAttribute("loginMember");

        if (loginMember != null) {
            return true; // 세션에 loginMember 값이 있는 경우 인터셉터를 통과시킴
        } else {
            throw new BusinessException(ErrorCode.INVALID_LOGIN); // 세션에 loginMember 값이 없는 경우 BusinessException 발생
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러 메서드 실행 후, 뷰 렌더링 전에 실행되는 코드 작성
        // 필요한 후처리 작업 수행
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 뷰 렌더링 후 실행되는 코드 작성
        // 필요한 마무리 작업 수행
    }
}
