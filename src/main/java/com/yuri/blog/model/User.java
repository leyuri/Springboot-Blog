package com.yuri.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User 클래스가 MySQL에 테이블이 자동 생성이 된다. 
// @DynamicInsert //insert 시에 null인 필드를 제외시켜준다. 
public class User {
	
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	//oracle : sequence, mysql :  auto_increment 로 간다는 뜻~
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; //아이디
	

	@Column(nullable = false, length = 100) // 123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//	@ColumnDefault("'user'")
	//DB는 RoleType이라는 게 없다. 
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는 게 좋다. //ADMIN, USER
	
	
	
//	@ColumnDefault("'user'") //데이터베이스에서 varchar로 사용할 것이기 때문에 ' ' 추가
//	private String role; // Enum을 쓰는 게 좋다. 왜? 도메인을 만들 수 있음 
//	//admin, user, manager 권한을 줘서... 실수로 managerrrr 로 오타를 낼 수도 있다. 
//	//enum을 사용하면 도메인을 설정할 수 있다. 어떤 범위가 정해졌다는 것을 의미, 근데 일단은 string으로...
	
	@CreationTimestamp //시간이 자동 입력 , 회원가입을 할때 위의 정보들이 다 insert 될 때 id 부분을 비워놓아도 자동으로 들어감, Timestamp 이것도  
	private Timestamp createDate;
	 
}