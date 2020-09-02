package com.yuri.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yuri.blog.config.auth.PrincipalDetail;
import com.yuri.blog.service.BoardService;

@Controller
public class BoardController {
	
	 @Autowired
	 private BoardService boardService;
	 
	 
	 
	// 컨트롤로에서 세션을 어떻게 찾는지? 
	// @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) { 
		model.addAttribute("boards",boardService.글목록(pageable));
		return "index"; //viewResolver 작동!!
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}
	
	//	@PathVariable를 이용하여 id값을 받는다. 그리고 모델을 만든다. 
	// 모델은 해당 데이터를 가지고 view까지 이동하는 것이다. 
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		// board 데이터를, boardService.글상세보기를 그대로 실행시켜준다. 재활용할 것. 어차피 그 글을 그대로 들고갈 것이기 때문에 
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
		
	// User 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}