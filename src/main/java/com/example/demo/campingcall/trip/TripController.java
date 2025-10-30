package com.example.demo.campingcall.trip;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.campingcall.comment.domain.Comment;
import com.example.demo.campingcall.comment.service.CommentService;
import com.example.demo.campingcall.common.Paging;
import com.example.demo.campingcall.trip.domain.Trip;
import com.example.demo.campingcall.trip.service.TripService;

@RequestMapping("/trip")
@Controller
public class TripController {
	
	private TripService tripService;
	
	private CommentService commentService;
	
	public TripController(TripService tripService
						, CommentService commentService) {
		this.tripService = tripService;
		this.commentService = commentService;
	}
	
	@GetMapping("/tripList")
	public String tripList(@RequestParam(name="page", required=false) Integer page
						,@RequestParam(name="keyword", required=false) String keyword
						,@RequestParam(name="code", required=false) String code
						,Model model) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		
		String url = "/trip/tripList?";
		if(keyword == null || keyword.length() < 1) {
			keyword = null;
		}else {
			url = url + "keyword=" + keyword;
		}
		
		model.addAttribute("keyword",keyword);
		
		String doCode = "";
		String sigunguCode = "";
		
		if(code != null) {
			if(!code.contains("/")) {
				model.addAttribute("msg", "잘못된 접근입니다");
				model.addAttribute("url", "/trip/tripList");
				
				return "board/alert";
			}
			
			if(code.replaceAll("/", "").length() == 0) {
				
			}else {
				String[] codeSplit = code.split("/");
				
				doCode = codeSplit[0];
				sigunguCode = codeSplit[1];
				
				url = url + "&code=" + code;
			}
		}else {
			doCode = null;
			sigunguCode = null;
		}
		
		List<Trip> tripList = new ArrayList<>();
		
		
		Paging paging;
		try {
			tripList = tripService.getTripList(page, doCode, sigunguCode);
			// 페이징
			paging = new Paging(tripList.get(0).getAreaBaseList().getAllCount());
		}catch(NullPointerException e) {
			model.addAttribute("msg", "검색결과가 존재하지 않습니다");
			model.addAttribute("url", "/trip/tripList");
			
			return "board/alert";
		}
		
		

		List<Integer> pagingList = paging.getPagingList(10,5,page);
		
		int end = 0;
		for(int i = 0; i < pagingList.size(); i++) {
			end = pagingList.get(i);
		}
		
		url = url + "&page=";
		model.addAttribute("tripList", tripList);
		model.addAttribute("paging", paging);
		model.addAttribute("page", page);
		model.addAttribute("first", paging.getPageList().get(0));
		model.addAttribute("end", end);
		model.addAttribute("url", url);
		
		return "trip/tripList";
	}
	
	@GetMapping("/detail/{contentId}")
	public String tripDetail(@PathVariable(name="contentId", required=false) String contentId
							,Model model) {
		
		if(contentId == null) {
			model.addAttribute("msg", "잘못된 접근입니다");
			model.addAttribute("url", "/trip/tripList");
			
			return "board/alert";
		}
		
		Trip trip = tripService.getTrip(contentId);
		
		List<Comment> commentList = commentService.boardCommentList(Integer.parseInt(contentId), 2);
		
		model.addAttribute("trip",trip);
		model.addAttribute("commentList", commentList);
		
		return "trip/detail";
	}
	
}
