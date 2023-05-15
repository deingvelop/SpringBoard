package com.example.springboard.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
		
		Logger log = LogManager.getLogger("case3");
		
		if (e instanceof BusinessException) {
			// BusinessException이 발생한 경우
	        log.info(e.getMessage());
	        e.printStackTrace();
			
			ModelAndView modelAndView = new ModelAndView("error");
	        modelAndView.addObject("errorCode", (((BusinessException) e).getErrorCode().getCode()));
	        modelAndView.addObject("errorMessage", (((BusinessException) e).getErrorCode().getMessage()));
	        modelAndView.addObject("showAlert", true);
	        return modelAndView;
	        
		} else {
	    	// 다른 예외일 경우
	        log.error("Unhandled exception occurred: ", e);
	        e.printStackTrace();

	        ModelAndView modelAndView = new ModelAndView("error");
	        modelAndView.addObject("errorCode", response.getStatus());
	        modelAndView.addObject("errorMessage", e.getMessage());

	        log.info(modelAndView.getModel().get("errorCode"));
	        log.info(modelAndView.getModel().get("errorMessage"));
	        
	        return modelAndView;
	    }
		
	}
	
	
}
