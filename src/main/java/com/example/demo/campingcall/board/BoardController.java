package com.example.demo.campingcall.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.campingcall.board.domain.Board;
import com.example.demo.campingcall.board.service.BoardService;

import jakarta.servlet.http.HttpServletResponse;

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
		
		List<Board> boardList = new ArrayList<>();
		
		if(page == null && search == null) {
			// 기본 초기화면이므로 page=1에 대한 데이터 얻기?
			// 강제 redirect로 page=1로 보내기?
			try {
				response.sendRedirect("/board/list-view?page=1");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(page == null) {
			// 검색을 통한 리스트 데이터 얻기
		}else if(search == null) {
			// 페이징을 통한 리스트 데이터 얻기
			boardList = boardService.boardList(page);
		}
		
		// 페이징 시작
		Map<String, Integer> resultPaging = new HashMap<>();
		
		int count = boardService.countByAll(); // 데이터 총 개수
		int avg = count / 7 + 1;
		int mypage = (page - 1) / 5;
		
		int first = mypage * 5 + 1;
		int second = mypage * 5 + 2;
		int third = mypage * 5 + 3;
		int fourth = mypage * 5 + 4;
		int fifth = mypage * 5 + 5;
		
		resultPaging.put("first", first);
		resultPaging.put("second", second);
		resultPaging.put("third", third);
		resultPaging.put("fourth", fourth);
		resultPaging.put("fifth", fifth);
		
		Set<String> keySet = resultPaging.keySet();
		
		for(String key : keySet) {
			int sum = resultPaging.get(key);
			
			if(sum <= avg) {
				continue;
			}else {
				resultPaging.put(key, null);
			}
		}
		resultPaging.put("end", avg);
		resultPaging.put("now", page);
		resultPaging.put("group", mypage);
		
		model.addAttribute("resultList", boardList);
		model.addAttribute("paging", resultPaging);
		
		return "board/boardList";
	}
	
	@GetMapping("/detail-view/{id}")
	public String detail(@PathVariable("id") int id
						,Model model) {
		
		Board board = boardService.boardById(id);
		model.addAttribute("board", board);
		
		return "board/boardDetail";
	}
}
