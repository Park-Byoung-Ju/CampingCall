package com.example.demo.campingcall.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.campingcall.board.domain.Board;
import com.example.demo.campingcall.board.service.BoardService;
import com.example.demo.campingcall.common.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/list-view")
	public String boardList(@RequestParam(name="page", required=false) Integer page
						, @RequestParam(name="search", required=false) String search
						, HttpServletResponse response
						, Model model) {
		if(page == null) {
			page = 0;
		}
		
		if(search == null || search.equals("null")) {
			search = null;
		}
		Paging paging;
		List<Board> boardList = new ArrayList<>();
		
		if((page == 0 && search == null )|| search == null) {
			if(page == 0) {
				page = 1;
			}
			boardList = boardService.boardList(page);			
			paging = new Paging(boardService.countByAll());
			
		}else {
			if(page == 0) {
				page = 1;
			}
			// 검색을 통한 리스트 데이터 얻기
			boardList = boardService.boardSearchList(search);
			paging = new Paging(boardService.boardSearchCount(search));
		}
		
		// 페이징
		
		List<Integer> pagingList = paging.getPagingList(7, 5, page);
		paging.setPageList(pagingList);
		int size = pagingList.size() - 1;
		
		int start = pagingList.get(0);
		int end = pagingList.get(size);
		
		if(search == null) {
			search = null;
		}
		
		model.addAttribute("search", search);
		model.addAttribute("page", page);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("pagingList", paging);
		model.addAttribute("resultList", boardList);
	
		return "board/boardList";
	}
	
	@GetMapping("/detail-view/{id}")
	public String detail(@PathVariable("id") int id
						,Model model) {
		
		Board board = boardService.boardById(id);
		
		
		model.addAttribute("board", board);
		
		return "board/boardDetail";
	}
	
	@GetMapping("/update-view/{id}")
	public String update(@PathVariable("id") int id
						,HttpServletRequest request
						,Model model) {
		
		HttpSession session = request.getSession();
		Integer sessionUserId = (Integer)session.getAttribute("userId");
		
		Board board = boardService.boardById(id);
		
		int userId = board.getUser().getId();
		
		if(sessionUserId == null || sessionUserId != userId) {
			model.addAttribute("msg", "잘못된 접근입니다");
			model.addAttribute("url", "/board/list-view");
			
			return "board/alert";
		}
		
		model.addAttribute("board", board);
		
		return "board/boardUpdate";
	}
	
	@GetMapping("/create-view")
	public String createView(HttpServletRequest request
							,Model model) {
		
		HttpSession session = request.getSession();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			model.addAttribute("msg","잘못된 접근 입니다. 로그인을 해주세요");
			model.addAttribute("url", "/board/list-view");
			return "board/alert";
		}
		
		return "board/boardCreate";
	}
}
