package com.yuri.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.blog.config.auth.PrincipalDetail;
import com.yuri.blog.dto.ReplySaveRequestDto;
import com.yuri.blog.dto.ResponseDto;
import com.yuri.blog.model.Board;
import com.yuri.blog.model.Reply;
import com.yuri.blog.model.RoleType;
import com.yuri.blog.model.User;
import com.yuri.blog.service.BoardService;
import com.yuri.blog.service.UserService;


@RestController
// Controller + Responsebody
public class BoardApiController {

	
	@Autowired
	private BoardService boardService;
	
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) { 
		boardService.글쓰기(board, principal.getUser());
		String a = "redirect";
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		System.out.println("BoardApiController : update : id : "+id);
		System.out.println("BoardApiController : update : board : "+board.getTitle());
		System.out.println("BoardApiController : update : board : "+board.getContent());
		boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 원래 데이터를 받을 때 dto를 만들어주는 게 좋다. 
	// dto 를 사용하지 않은 이유는 ...? 프로그램이 거대해지면 왔다갔다 하는
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		boardService.댓글쓰기(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
		boardService.댓글삭제(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}	
}