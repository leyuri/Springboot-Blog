package com.yuri.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		
		//파일 리턴 기본경로: src/main/resources/static
		//리턴명 : /home.html
		//풀경로: src/main/resources/statichome.html
		return "/home.html";
	}
	
	// src/main/resources/test.jsp 안에 있을 경우 찾지 못한다. 
	//	@GetMapping("/temp/jsp")
	//	public String tempJsp() {
	//		return "/test.jsp";
	//	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		
		//prefix : /WEB-INF/views/
		//suffix: .jsp
		//풀경로:  /WEB-INF/views/test.jsp
		return "test";
	}
	
}
