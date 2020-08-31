package com.yuri.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yuri.blog.model.User;


//DAO
//자동으로 bean 등록이 된다. 
//bean으로 등록된다는 것은 스프링 ioc에서 객체를 가지고 있나요? 라고 물어보는 것이다. 
//Repository 생략 가능하다. 


//JpaRepository는 User table이 관리하는 레파지토리이다. 이 user table의 primary key는 Integer이다. 
public interface UserRepository extends JpaRepository<User, Integer>{

}


// JPA Naming 전략
// SELECT * FROM user WHERE username = ? AND password = ?;
// User findByUsernameAndPassword(String username, String password);
// Navtive Query, 두번째 방법, 근데 지금은 간단하니까 주석처리~ 
//	@Query(value=" SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//	User login(String username, String password);
 