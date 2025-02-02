package com.example.demo.campingcall.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.campingcall.board.domain.Board;
import com.example.demo.campingcall.board.repository.BoardRepository;
import com.example.demo.campingcall.comment.domain.Comment;
import com.example.demo.campingcall.comment.service.CommentService;
import com.example.demo.campingcall.common.FileManager;
import com.example.demo.campingcall.user.domain.User;
import com.example.demo.campingcall.user.service.UserService;

import jakarta.transaction.Transactional;

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
	
	// 게시글 검색
	public List<Board> boardSearchList(String search){
		
		List<Board> boardList = boardRepository.findTop5ByTitleContains(search);
		
		if(boardList == null || boardList.size() < 1) {
			return null;
		}
		
		for(int i = 0; i < boardList.size(); i++) {
			User user = userService.userById(boardList.get(i).getUserId());
			
			List<Comment> commentList = commentService.boardCommentList(boardList.get(i).getId(), 1);
			
			//boardList.get(i).setTitle(boardList.get(i).getTitle().replace(search, "<b>" + search + "</b>"));
			boardList.get(i).setCommentList(commentList);
			boardList.get(i).setUser(user);
		}
		
		return boardList;
	}
	
	// 검색데이터 개수
	public int boardSearchCount(String search) {
		return boardRepository.countByTitleContains(search);
	}
	
	// 게시글 삽입
	public Board boardCreate(int userId
							, String title
							, String contents
							, MultipartFile imagePath) {
		String imagePathEncoding = FileManager.saveFile(userId, imagePath);
		
		Board board = Board.builder()
							.userId(userId)
							.title(title)
							.contents(contents)
							.imagePath(imagePathEncoding)
							.build();
							
		
		try {
			Board reulstBoard = boardRepository.save(board);
			
			return reulstBoard;
		}catch(Exception e) {
			return null;
		}
	}
	
	// 게시글 리스트
	public List<Board> boardList(int page){
		int start = (page - 1) * 7; 			  // 0  7   14
		int end = 7;     			 // 7  14  21
		
		List<Board> resultBoard = new ArrayList<>();
		resultBoard = boardRepository.boardList(start, end);
		
		if(resultBoard == null || resultBoard.size() < 1 || resultBoard.get(0).getContents() == null) {
			return null;
		}
		
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
	
	// 게시글 삭제
	@Transactional 
	public boolean boardDelete(int id) {
		
		Optional<Board> optionalBoard = boardRepository.findById(id);
		
		if(optionalBoard == null) {
			return false;
		}
		
		Board selectBoard = optionalBoard.get();
		
		FileManager.removeFile(selectBoard.getImagePath());
		
		boardRepository.deleteById(id);

		optionalBoard = boardRepository.findById(id);
		Board board = optionalBoard.orElse(null);
		
		
		if(board == null) {
			List<Comment> commentList = commentService.boardCommentList(id, 1);
			if(commentList == null) {
				return true;
			}else {
				return commentService.boardCommentAllDelete(id, 1);
			}

		}else {
			return false;
		}
	}
	
	// 게시글 수정
	@Transactional
	public boolean boardUpdate(int id, String title, String contents, MultipartFile imagePath, int userId) {
		// 이미지 처리
		String imagePathEncoding = FileManager.saveFile(userId, imagePath);
		//
		Optional<Board> optionalBoard = boardRepository.findById(id);
		
		if(optionalBoard.isPresent()) {
			Board board = optionalBoard.get();
			
			if(!imagePathEncoding.isEmpty()) {
				FileManager.removeFile(board.getImagePath());
			}
			
			board = board.toBuilder()
						.title(title)
						.contents(contents)
						.imagePath(imagePathEncoding)
						.build();
			
			try {
				boardRepository.save(board);
				return true;
			} catch(Exception e) {
				return false;
			}
		}
		
		return false;

	}
}
