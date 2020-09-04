package com.yuri.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.blog.model.RoleType;
import com.yuri.blog.model.User;
import com.yuri.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		//orElseThrow로 하면 오류난 것으로 처리될 수 있기 때문에....
		User user = userRepository.findByUsername(username).orElseGet(()->{
			// 회원을 찾을때 회원이 없을 경우 빈객체를 리턴해준다. 빈객체 말고 강제로 만들어서 리턴해줄 수도 있다. 
			return new User();
		});
		return user;
	}
	
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //1234 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update 문을 날려준다. 
		// 영속화니까 persistance 라고 하자
		// 혹시 몾찾을 수도 있으니까 , null 값일 수도 있으니까 orElseThrow를 걸어줌
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		// Validate 체크 => oauth 필드에 값이 없으면 수정 가능
		// getOauth 이게 있는 사람들은 패스워드를 집어넣든 말든  절대 수정되지 않도록 .. 
		// null 이거나 비어있으면 
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword); //password 수정해주기
			persistance.setEmail(user.getEmail()); //영속화 되어있는 곳에서 setEmail를 통해 변경해준다
		}
	
		
		// 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 된다는 의미이다. 
		// commit 이 자동으로 된다는 것은 영속화된 persistance 객체의 변화가 감지되면, 더티체킹이 되어 변화된 것들을 업데이트 update 문을 자동으로 날려준다. 
		

	}
}