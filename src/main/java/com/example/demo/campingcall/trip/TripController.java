package com.example.demo.campingcall.trip;


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
						,Model model) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		
		
		List<Trip> tripList = tripService.getTripList(page, null, null);
		
		// 페이징
		Paging paging = new Paging(tripList.get(0).getAreaBaseList().getAllCount());

		List<Integer> pagingList = paging.getPagingList(10,5,page);
		
		int end = 0;
		for(int i = 0; i < pagingList.size(); i++) {
			end = pagingList.get(i);
		}
		
		model.addAttribute("tripList", tripList);
		model.addAttribute("paging", paging);
		model.addAttribute("page", page);
		model.addAttribute("first", paging.getPageList().get(0));
		model.addAttribute("end", end);
		
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
	
	@GetMapping("/search")
	public String tripSearch() {
		return "trip/search";
	}
}
