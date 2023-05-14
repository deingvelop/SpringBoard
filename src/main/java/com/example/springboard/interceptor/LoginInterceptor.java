package com.example.springboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러 메서드 실행 전에 실행되는 코드 작성
        // 로그인 여부를 확인하고 필요한 작업 수행
        // 예: 로그인되지 않은 경우 예외를 던지거나 로그인 페이지로 리다이렉트

        return true; // 계속 진행
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