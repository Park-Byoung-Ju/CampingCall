package com.example.demo.campingcall.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.campingcall.board.domain.Board;
import com.example.demo.campingcall.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/board")
@RestController
public class BoardRestController {
	
	private BoardService boardService;
	
	public BoardRestController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@PostMapping("/create")
	public Map<String, Object> create(@RequestParam("title") String title
						,@RequestParam("contents") String contents
						,@RequestParam(name="imagePath", required=false) MultipartFile imagePath
						,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int userId = (int)session.getAttribute("userId");
		
		Board board = boardService.boardCreate(userId, title, contents, imagePath);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if(board == null) {
			resultMap.put("result", false);
		}else {
			resultMap.put("result", true);
		}
		
		resultMap.put("board", board);
		
		return resultMap;
	}

	@PutMapping("/update")
	public boolean update(@RequestParam("id") int id
						,@RequestParam("title") String title
						,@RequestParam("contents") String contents
						,@RequestParam(name="imagePath", required=false) MultipartFile imagePath
						,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		return boardService.boardUpdate(id, title, contents, imagePath, userId);
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") int id) {

		return boardService.boardDelete(id);
	}
}
