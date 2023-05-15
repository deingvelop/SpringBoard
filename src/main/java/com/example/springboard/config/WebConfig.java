/*
 * package com.example.springboard.config;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.web.servlet.config.annotation.InterceptorRegistry; import
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 * 
 * import com.example.springboard.interceptor.LoginInterceptor;
 * 
 * @Configuration public class WebConfig implements WebMvcConfigurer {
 * 
 * @Override public void addInterceptors(InterceptorRegistry registry) {
 * registry.addInterceptor(new LoginInterceptor()) .addPathPatterns("/board/**",
 * "/post/**") // 인터셉터 적용할 URL 패턴 설정 .excludePathPatterns("/join", "/login",
 * "/logout"); // 인터셉터 제외할 URL 패턴 설정 } }
 */