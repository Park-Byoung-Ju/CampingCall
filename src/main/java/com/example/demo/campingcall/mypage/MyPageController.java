package com.example.demo.campingcall.mypage;

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
import com.example.demo.campingcall.common.Paging;
import com.example.demo.campingcall.mypage.domin.Booking;
import com.example.demo.campingcall.mypage.serivce.MyPageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/mypage")
@Controller
public class MyPageController {
	
	private MyPageService myPageService;
	
	private CampService campService;
	
	public MyPageController(MyPageService myPageService
							, CampService campService) {
		
		this.myPageService = myPageService;
		this.campService = campService;
	}
	
	
	@GetMapping("/payList")
	public String payList(@RequestParam(name="page", required=false) Integer page
						,HttpServletRequest request
						,Model model) {
			
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		Integer userId = (Integer) session.getAttribute("userId");

		if(name == null || userId == null) {
			model.addAttribute("msg", "로그인을 해주세요");
			model.addAttribute("url", "/main");
			
			return "board/alert.html";
		}
		
		if(page == null || page <= 0) {
			page = 1;
		}
		
		int items = (page - 1) * 5;
		
		List<CampBooking> campBookingList = myPageService.getBookingList(userId, items, 5);
		
		if(campBookingList != null) {
			List<Booking> bookingList = new ArrayList<>();
			
			for(int i = 0; i < campBookingList.size(); i++) {
				Booking booking = new Booking();
				booking.setCampBooking(campBookingList.get(i));
				
				Camp camp = campService.getDetail(campBookingList.get(i).getKeyword());
				
				if(camp != null) {
					booking.setCamp(camp);
					bookingList.add(booking);
				}
			}
			
			Paging paging = new Paging(myPageService.getCountUserId(userId));
			
			List<Integer> pagingList = paging.getPagingList(5, 5, page);
			
			model.addAttribute("pagingList", pagingList);
			model.addAttribute("bookingList", bookingList);
			model.addAttribute("page", page);
			model.addAttribute("count", paging.getAllPage());
		}else {
			model.addAttribute("pagingList", null);
			model.addAttribute("bookingList", null);
		}
		
		return "pay/payList";
	}
	
	@GetMapping("/main")
	public String myPage(HttpServletRequest request
						,Model model) {
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		Integer userId = (Integer) session.getAttribute("userId");
		
		if(name == null || userId == null) {
			model.addAttribute("msg", "로그인을 해주세요");
			model.addAttribute("url", "/main");
			
			return "board/alert.html";
		}
		
		model.addAttribute("name", name);
		
		List<CampBooking> campBookingList = myPageService.getMainBookingList(userId);
		
		if(campBookingList != null) {
			List<Booking> bookingList = new ArrayList<>();
			
			for(int i = 0; i < campBookingList.size(); i++) {
				Booking booking = new Booking();
				booking.setCampBooking(campBookingList.get(i));
				
				Camp camp = campService.getDetail(campBookingList.get(i).getKeyword());
				
				if(camp != null) {
					booking.setCamp(camp);
					bookingList.add(booking);
				}
			}
			
			model.addAttribute("bookingList", bookingList);
			
			
		}else {
			model.addAttribute("bookingList", null);
		}
		
		
		return "user/mypage";
	}

}
