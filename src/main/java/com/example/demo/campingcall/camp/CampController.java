package com.example.demo.campingcall.camp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.camp.domain.CampBooking;
import com.example.demo.campingcall.camp.service.CampService;
import com.example.demo.campingcall.comment.domain.Comment;
import com.example.demo.campingcall.comment.service.CommentService;
import com.example.demo.campingcall.common.Paging;
import com.example.demo.campingcall.mypage.serivce.MyPageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/camp")
@Controller
public class CampController {
	
	private CampService campService;
	
	private CommentService commentService;
	
	private MyPageService myPageService;
	
	public CampController(CampService campService
						, CommentService commentService
						, MyPageService myPageService) {
		this.campService = campService;
		this.commentService = commentService;
		this.myPageService = myPageService;
	}

	@GetMapping("/campList")
	public String campList(@RequestParam(name="page", required=false) Integer page
						,Model model) {
		
		if(page == null) {
			page = 1;
		}
		
		List<Camp> campList = campService.getList(page, 10);
		
		Paging paging = new Paging(campList.get(0).getAllCount());

		List<Integer> pagingList = paging.getPagingList(10,5,page);
		
		int end = 0;
		for(int i = 0; i < pagingList.size(); i++) {
			end = pagingList.get(i);
		}
		
		
		model.addAttribute("campList", campList);
		model.addAttribute("paging", paging);
		model.addAttribute("page", page);
		model.addAttribute("end", end);
		
		return "camp/campingList";
	}
	
	@GetMapping("/detail")
	public String campDetail(@RequestParam(name="keyword", required=false) String keyword
							,@RequestParam(name="contentsId", required=false) int contentId
							,Model model) {
		
		Camp camp = campService.getDetail(keyword);
		List<Comment> commentList = commentService.boardCommentList(contentId, 3);

			
		
		model.addAttribute("camp", camp);
		model.addAttribute("commentList", commentList);
		
		return "camp/detail";
	}
	
	@GetMapping("/search")
	public String campSearch(@RequestParam(name="keyword", required=false) String keyword
							,@RequestParam(name="page", required=false) Integer page
							,Model model) {
		
		if(page == null || page < 1) {
			page = 1;	
		}
		
		if(keyword == null) {
			keyword = "";
		}
		
		List<Camp> campList = new ArrayList<>();
		campList = campService.getSearchList(keyword,page);
		
		if(campList != null) {
			model.addAttribute("campList", campList);
		}
		
		System.out.println("count : " + campList.get(0).getAllCount());
		Paging paging = new Paging(campList.get(0).getAllCount());

		List<Integer> pagingList = paging.getPagingList(10,5,page);
		
		int end = 0;
		for(int i = 0; i < pagingList.size(); i++) {
			end = pagingList.get(i);
			System.out.println("list : " + end);
		}
		
		model.addAttribute("keyword",keyword);
		model.addAttribute("paging", paging);
		model.addAttribute("page", page);
		model.addAttribute("end", end);
		
		return "camp/search";
	}
	
	@GetMapping("/payment")
	public String paymentFinish(@RequestParam(name = "id", required=false) Integer id
								,@RequestParam(name = "keyword", required=false) String keyword
								,Model model
								,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		
		if(id == null || keyword == null) {
			model.addAttribute("msg", "잘못 된 접근입니다");
			model.addAttribute("url", "/main");
			
			return "board/alert.html";
		}
		
		if(name == null) {
			model.addAttribute("msg", "로그인을 해주세요");
			model.addAttribute("url", "/main");
			
			return "board/alert.html";
		}
		
		CampBooking campBooking = myPageService.getBooking(id);
		Camp camp = campService.getDetail(keyword);

		model.addAttribute("keyword",keyword);
		model.addAttribute("campBooking", campBooking);
		model.addAttribute("camp", camp);
		model.addAttribute("name", name);
		return "pay/payment";
	}
}
