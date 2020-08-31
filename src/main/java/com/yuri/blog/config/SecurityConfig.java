package com.yuri.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// 이 세개는 시큐리티의 세트이다... 이해가 안되면 그냥 하면 됨...^^
// 빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration //빈등록(IoC관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다. 
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	 
	
	@Bean //IoC가 되요!!!
	// 이 함수가 리턴하는 new BCryptPasswordEncoder(); 이 값을 스프링이 관리한다. 필요할 때마다 가져다 사용하면 된다. 
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트 시 걸어두는 게 좋음)
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
				.permitAll()
				.anyRequest() //이게 아닌 다른 모든 요청은 
				.authenticated()	//인증이 되어야 함
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
				
	}
}
