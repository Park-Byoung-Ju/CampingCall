package com.example.demo.campingcall.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.board.domain.Board;
import com.example.demo.campingcall.board.repository.BoardRepository;

@Service
public class BoardService {

	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<Board> boardList(int page){
		int start = (page - 1) * 7; 			  // 0  7   14
		int end = page * 7 - 1;     			 // 6  13  20
		
		int allCount = boardRepository.listByAllCount();
		
		if(end >= allCount) {
			end = allCount;
		}
		
		if(start >= end) {
			start = end;
		}
		
		return boardRepository.boardList(start, end);	
	}
	
	public int countByAll() {
		return boardRepository.listByAllCount();
	}
}
