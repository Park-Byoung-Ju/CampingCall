package com.example.demo.campingcall.plan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/plan")
@Controller
public class PlanController {
	
	@GetMapping("/main")
	public String planMain() {
		
		return "plan/planMain";
	}
	
	@GetMapping("/room")
	public String planRoom() {
		// 방 primary key를 받는 파라미터 추가할것
		
		return "plan/planRoom";
	}
	

}
