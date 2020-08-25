package com.yuri.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.blog.model.RoleType;
import com.yuri.blog.model.User;
import com.yuri.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	
	// 스프링이 @RestController 어노테이션을 읽어서 DummyControllerTest 를 메모리에 띄어줄 때 얘는 null, 
	// 하지만 Autowired 어노테이션 붙이면 얘도 같이 메모리에 띄어줄 수 있다. 
	//UserRepository 타입으로 스프링이 관리하고 있는 객체가 있다면 userRepository에 쏘옥 넣어준다. 
	@Autowired //의존성 주입(DI)
	private  UserRepository userRepository;
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http와 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value (약속된 규칙)
		System.out.println("id: "+user.getId());
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		System.out.println("role :"+user.getRole());
		System.out.println("createDate: "+user.getCreateDate());
		
		
		user.setRole(RoleType.USER);
		// user 객체를 집어넣으면 회원가입이 된다!
		userRepository.save(user);
		return "회원가입이 완료되었습니다 :)";
	}
}
