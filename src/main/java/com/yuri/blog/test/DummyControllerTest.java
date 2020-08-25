package com.yuri.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.blog.model.RoleType;
import com.yuri.blog.model.User;
import com.yuri.blog.repository.UserRepository;

// html 파일이 아니라 data를 리턴해주는 controller = Restcontroller
@RestController
public class DummyControllerTest {
	
	
	// 스프링이 @RestController 어노테이션을 읽어서 DummyControllerTest 를 메모리에 띄어줄 때 얘는 null, 
	// 하지만 Autowired 어노테이션 붙이면 얘도 같이 메모리에 띄어줄 수 있다. 
	//UserRepository 타입으로 스프링이 관리하고 있는 객체가 있다면 userRepository에 쏘옥 넣어준다. 
	@Autowired //의존성 주입(DI)
	private  UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}

	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=1, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);

		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// return의 user객체를 return 받을 것!
	// {}주소로 파라미터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// user/4을 찾을 때 내가 데이터베이스에서 못 찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null 이 리턴이 되잖어..이 프로그램에 문제가 있지 않겠니?..
		// optional 로 너의 User 객체를 감싸서 가져올테니 null 인지 아닌지 판단해서 return 해! 
		
		// 람다식
		// User user = userRepository.findById(id).orElseThrow(()-> {
		// 	return new IllegalArgumentException("해당 유저는 없습니다.");
		// });
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id:" +id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = java object
		// 변환 ( 웹 브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConvert라는 얘가 응답시에 자동 작동
		// 만약 java 
		return user;
		
		 
	}
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
		
		
		//default 값을 회원가입 할때 미리 넣어버리면 된다. 
		user.setRole(RoleType.USER);
		// user 객체를 집어넣으면 회원가입이 된다!
		userRepository.save(user);
		return "회원가입이 완료되었습니다 :)";
	}
}
