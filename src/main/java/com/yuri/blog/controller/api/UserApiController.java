package com.yuri.blog.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.blog.dto.ResponseDto;
import com.yuri.blog.model.User;


// 데이터만 리턴해주기 때문에 
@RestController
public class UserApiController {
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { //요청 받는게 json이니까 requestbody
		System.out.println("User Api Controller : save 호출됨 ");
		return new ResponseDto<Integer>(200,1);
	}
}
