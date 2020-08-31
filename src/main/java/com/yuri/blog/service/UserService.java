package com.yuri.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.blog.model.User;
import com.yuri.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	// 회원가입 전체 서비스가 하나의 트랜잭션으로 묶이게 된다
	// 전체가 성공을 하면 커밋이 될 것임
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
}