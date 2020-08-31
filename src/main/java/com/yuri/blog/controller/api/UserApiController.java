package com.yuri.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.blog.dto.ResponseDto;
import com.yuri.blog.model.RoleType;
import com.yuri.blog.model.User;
import com.yuri.blog.service.UserService;


// 데이터만 리턴해주기 때문에 
@RestController
public class UserApiController {

	
	@Autowired	// DI : 스프링이 컴포넌트 스캔할 때 서비스라는 어노테이션이 붙어있는 클래스를 보는 순간 스프링 빈에 등록해 메모리를 띄어준다. 
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	//요청 받는게 json이니까 requestbody
	public ResponseDto<Integer> save(@RequestBody User user,  HttpSession session) { // username, password, email
		System.out.println("UserApiController : save 호출됨");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
		// result가 1이면 성공 -1이면 실패
	} 
}