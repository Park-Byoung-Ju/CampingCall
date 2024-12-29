package com.example.demo.campingcall.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.board.domain.Board;
import com.example.demo.campingcall.board.repository.BoardRepository;
import com.example.demo.campingcall.user.domain.User;
import com.example.demo.campingcall.user.repository.UserRepository;
import com.example.demo.campingcall.user.service.UserService;

@Service
public class BoardService {

	private BoardRepository boardRepository;
	private UserService userService;
	
	public BoardService(BoardRepository boardRepository
						,UserService userService) {
		this.boardRepository = boardRepository;
		this.userService = userService;
	}
	
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
	
	public int countByAll() {
		return boardRepository.listByAllCount();
	}
	
	public Board boardById(int id) {
		Optional<Board> optionalBoard = boardRepository.findById(id);
		Board board = optionalBoard.orElse(null);
		
		User user = userService.userById(board.getUserId());
		
		board.setUser(user);
		
		return board;
	}
}
