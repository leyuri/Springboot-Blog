package com.yuri.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //어디에서 발생하던 간에 이쪽으로 오게 하기 위해서, 전역적으로 예외 처리
@RestController
public class GlobalExceptionHandler {
	
	//	IllegalArgumentException 이 발생하면 스프링은 그 exception에 대한 error를 이 함수에게 전달해준다. 
	@ExceptionHandler(value=Exception.class)
	public String handlerArgumentException(Exception e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
	// 다른 exception 을 받고 싶으면 여기다가 적으면 된다. 
	//	@ExceptionHandler(value=IllegalArgumentException.class)
	//	public String handlerArgumentException(IllegalArgumentException e) {
	//		return "<h1>"+e.getMessage()+"</h1>";
	//	}
}
