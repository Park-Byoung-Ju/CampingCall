package com.example.demo.campingcall.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		List<Board> boardList;
		
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
		
		Map<String, Integer> resultPaging = new HashMap<>();
		
		int count = boardService.countByAll();
		int avg = count / 7 + 1; // 게시글 6 개 기준 / 7 하면 0이 return 되어서 1을 더해준다
		
		int first = page - 4;
		int second = page - 3;
		int third = page - 2;
		int fourth = page - 1;
		int fifth = page;
		
		while(first < 1) {
			first++;
			second++;
			third++;
			fourth++;
			fifth++;
		}
		
		resultPaging.put("first", first);
		resultPaging.put("second", second);
		resultPaging.put("third", third);
		resultPaging.put("fourth", fourth);
		resultPaging.put("fifth", fifth);
		
		Set<String> keySet = resultPaging.keySet();
		
		for(String key : keySet) {
			int sum = resultPaging.get(key) + 2;
			
			if(sum <= avg) {
				resultPaging.put(key, sum);
			}else {
				resultPaging.put(key, null);
			}
		}
		resultPaging.put("end", avg);
		resultPaging.put("now", page);

		model.addAttribute("paging", resultPaging);
		
		return "board/boardList";
	}
}
