package com.example.demo.campingcall.mypage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.camp.domain.CampBooking;
import com.example.demo.campingcall.camp.service.CampService;
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
	public String payList(HttpServletRequest request
						,Model model) {
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");

		if(name == null) {
			model.addAttribute("msg", "로그인을 해주세요");
			model.addAttribute("url", "/main");
			
			return "board/alert.html";
		}
		
		return "pay/payList";
	}
	
	@GetMapping("/main")
	public String myPage(HttpServletRequest request
						,Model model) {
		
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("userName");
		
		if(name == null) {
			model.addAttribute("msg", "로그인을 해주세요");
			model.addAttribute("url", "/main");
			
			return "board/alert.html";
		}
		
		model.addAttribute("name", name);
		
		List<CampBooking> campBookingList = myPageService.getMainBookingList();
		
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
