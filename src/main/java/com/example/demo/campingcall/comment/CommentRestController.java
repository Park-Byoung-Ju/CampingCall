package com.example.demo.campingcall.comment;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.campingcall.comment.domain.Comment;
import com.example.demo.campingcall.comment.service.CommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {

	private CommentService commentService;
	
	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/create")
	public boolean commentCreate(@RequestParam("contentId") int contentId
								, @RequestParam("contents") String contents
								, @RequestParam("category") int category
								,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		if(userId == null) {
			return false;
		}
		
		Comment comment = commentService.boardCommentCreate(userId, contentId, contents, category);
		
		if(comment == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@DeleteMapping("/delete")
	public boolean commentDelete(@RequestParam("contentId") int contentId) {
		return commentService.boardCommentDelete(contentId);
	}
}
