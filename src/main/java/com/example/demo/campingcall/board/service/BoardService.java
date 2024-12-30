package com.example.demo.campingcall.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.board.domain.Board;
import com.example.demo.campingcall.board.repository.BoardRepository;
import com.example.demo.campingcall.comment.domain.Comment;
import com.example.demo.campingcall.comment.service.CommentService;
import com.example.demo.campingcall.user.domain.User;
import com.example.demo.campingcall.user.service.UserService;

@Service
public class BoardService {

	private BoardRepository boardRepository;
	private UserService userService;
	private CommentService commentService;
	
	public BoardService(BoardRepository boardRepository
						,UserService userService
						,CommentService commentService) {
		this.boardRepository = boardRepository;
		this.userService = userService;
		this.commentService = commentService;
	}
	
	// 게시글 리스트
	public List<Board> boardList(int page){
		int start = (page - 1) * 7; 			  // 0  7   14
		int end = 7;     			 // 7  14  21
		
		List<Board> resultBoard = new ArrayList<>();
		resultBoard = boardRepository.boardList(start, end);
		
		for(int i = 0; i < resultBoard.size(); i++) {
			int id = resultBoard.get(i).getUserId();
			
			User user = userService.userById(id);
			
			resultBoard.get(i).setUser(user);
		}
		
		return resultBoard;	
	}
	// 게시글 리스트 끝
	
	// 게시글 총 개수
	public int countByAll() {
		return boardRepository.listByAllCount();
	}
	// 게시글 총 개수
	
	// 게시글 상세 데이터 가져오기
	public Board boardById(int id) {
		Optional<Board> optionalBoard = boardRepository.findById(id);
		Board board = optionalBoard.orElse(null);
		
		User user = userService.userById(board.getUserId());
		
		board.setUser(user);
		
		List<Comment> listComment = commentService.boardCommentList(id, 1);
		
		board.setCommentList(listComment);
		
		return board;
	}
}
