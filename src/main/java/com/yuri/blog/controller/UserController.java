package com.yuri.blog.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuri.blog.config.auth.PrincipalDetail;
import com.yuri.blog.model.KakaoProfile;
import com.yuri.blog.model.OAuthToken;
import com.yuri.blog.model.User;
import com.yuri.blog.service.UserService;


// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용 
// static 이하에 있는 /js/**, /css/**, /image/** 

@Controller
public class UserController {
	
	@Value("${yuri.key}")
	private String yuriKey;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { //Data를 리턴해주는 컨트롤러 함수
		
		// POST 방식으로 key=value 데이터를 요청 (카카오쪽으로), a 태그를 통해서 post 방식 못함, a 태그는 무조건 get 방식임
		// Retrofit2
		// OkHttp
		// RestTemplate
		
		RestTemplate rt = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		// Content-type에 담는다는 것은  내가 지금 전송한 httpBodydata 가 key value 형태의 데이터라고 알려주는 것
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBody 오브젝트 생성 
		// Body 데이터를 담을 오브젝트를 만듦, 원래 이런 것들은 변수화를 시켜서 사용하는 것이 좋다. 
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "2170cc08bffc9520b36bca4817118401");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		// HttpHeader 와 HttpBody를 하나의 오브젝트에 담기 
		// kakaoTokenRequest 가 body 데이터와 header 값을 갖고 있는 엔티티가 된다. 
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		// 실제 요청
		
		// Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		
		// Gson, Json Simple, ObjectMapper json 데이터를 담을 수 있는 라이브러리
		// json 데이터를 자바 오브젝트에서 처리하기 위해서 자바 오브젝트로 바꾼 것
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰 : "+oauthToken.getAccess_token());
		
		RestTemplate rt2 = new RestTemplate();
		
		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		
		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
		);
		
		System.out.println(response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		// User 오브젝트 : username, password, email 필요
		System.out.println("카카오 아이디(번호) :" + kakaoProfile.getId());
		System.out.println("카카오 이메일:" + kakaoProfile.getKakao_account().getEmail());
		
		// 누군가 카카오 유저네임처럼 가입하는 회원이 있을 수도 있기 때문에 
		System.out.println("블로그서버 유저네임:"+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그서버 이메일:" + kakaoProfile.getKakao_account().getEmail());
		
		// UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
		System.out.println("블로그서버 패스워드:" + yuriKey);
		
		
		User kaKaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(yuriKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
	
		// 가입자 혹은 비 가입자인지 체크를 해서 처리해야 함 
		User originUser = userService.회원찾기(kaKaoUser.getUsername());
		
		//- 비가입자면 회원가입 하고 로그인 처리하고 가입자면 바로 로그인 처리하고 -//
		// originUSer가 null 이면 회원가입을 해야 함
		if(originUser.getUsername() == null) {
			System.out.println("기존 회원이 아닙니다___________________!!");
			userService.회원가입(kaKaoUser);
		}
		
		System.out.println("자동 로그인을 진행합니다.");
		
		// 로그인 처리 
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kaKaoUser.getUsername(), yuriKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) { 
		return "user/updateForm";
	}
}
