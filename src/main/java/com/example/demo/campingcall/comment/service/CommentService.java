package com.example.demo.campingcall.comment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.comment.domain.Comment;
import com.example.demo.campingcall.comment.repository.CommentRepository;
import com.example.demo.campingcall.user.domain.User;
import com.example.demo.campingcall.user.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserService userService;
	
	// 댓글 등록
	public CommentService(CommentRepository commentRepository
						, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}
	
	public Comment boardCommentCreate(int userId
								, int boardId
								, String comment
								, int category) {
		
		Comment buildComment = Comment.builder()
								.userId(userId)
								.contentId(boardId)
								.contents(comment)
								.category(category)
								.build();
		
		Comment resultComment = commentRepository.save(buildComment);

		// 성공 객체 실패 null
		return resultComment;
	}
	// 댓글 등록 끝
	
	// 댓글 삭제
	@Transactional
	public boolean boardCommentDelete(int commentId) {
		
		Optional<Comment> optionalComment = commentRepository.findById(commentId);
		Comment resultComment = optionalComment.orElse(null);
		
		if(resultComment == null) {
			return false;
		}else {			
			commentRepository.deleteById(commentId);			
		}
		
		optionalComment = commentRepository.findById(commentId);
		resultComment = optionalComment.orElse(null);
		
		if(resultComment == null) {
			return true;
		}else {			
			return false;	
		}
	}
	// 댓글 삭제 끝
	
	// 댓글 리스트 가져오기
	public List<Comment> boardCommentList(int id, int category){
		List<Comment> resultComment = commentRepository.findByContentIdAndCategory(id, category);
		
		for(int i = 0; i < resultComment.size(); i++) {
			int userId = resultComment.get(i).getUserId();
			
			User user = userService.userById(userId);
			
			resultComment.get(i).setUser(user);
		}
		
		return resultComment;
	}
	// 댓글 리스트 가져오기 끝
}
