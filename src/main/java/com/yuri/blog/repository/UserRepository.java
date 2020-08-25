package com.yuri.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.blog.model.User;


//DAO
//자동으로 bean 등록이 된다. 
//bean으로 등록된다는 것은 스프링 ioc에서 객체를 가지고 있나요? 라고 물어보는 것이다. 
//Repository 생략 가능하다. 


//JpaRepository는 User table이 관리하는 레파지토리이다. 이 user table의 primary key는 Integer이다. 
public interface UserRepository extends JpaRepository<User, Integer>{

}
