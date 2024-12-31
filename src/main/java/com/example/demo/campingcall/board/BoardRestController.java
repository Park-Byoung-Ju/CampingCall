package com.example.demo.campingcall.board;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.campingcall.board.service.BoardService;

@RequestMapping("/board")
@RestController
public class BoardRestController {
	
	private BoardService boardService;
	
	public BoardRestController(BoardService boardService) {
		this.boardService = boardService;
	}

	@PutMapping("/update")
	public boolean update(@RequestParam("id") int id
						,@RequestParam("title") String title
						,@RequestParam("contents") String contents
						,@RequestParam(name="imagePath", required=false) MultipartFile imagePath) {
		
		if(boardService.boardUpdate(id, title, contents, imagePath)) {
			return true;
		}else {			
			return false;
		}
		
	}
	
	@DeleteMapping("/delete")
	public boolean delete(@RequestParam("id") int id) {
		
		return boardService.boardDelete(id);
	}
}
