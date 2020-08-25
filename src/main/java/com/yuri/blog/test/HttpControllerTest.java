package com.yuri.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// @Controller : 사용자가 요청 -> 응답(html)파일 
// @RestController : 사용자가 요청 -> 응답(Data) 

@RestController
public class HttpControllerTest {
	
	private static final String TAG="HttpControllerTest: ";
	
	//http://localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("sarr").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter : "+m.getUsername()); 
		return "lombok test 완료";
	}
	//http://localhost:8080/http/get (select) 
	//	@GetMapping("/http/get")
	//	public String getTest(@RequestParam int id, @RequestParam String username) {
	//		return "get 요청 :" + id+","+username;
	//	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) { //id=1&username=yuri&password=1234&email=yuri@nate.com  //MessageConverter (스트링부트)
		return "get 요청 : "+ m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}

	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) { //MessageConverter (스트링부트)
		return  "post 요청 : "+ m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return  "put 요청 : "+ m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
