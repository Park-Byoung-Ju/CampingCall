package com.example.demo.campingcall.plan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/plan")
@Controller
public class PlanController {
	
	@GetMapping("/main")
	public String planMain() {
		
		return "plan/planMain";
	}
	
	@GetMapping("/planTime")
	public String planRoom(Model model) {
		// 방 primary key를 받는 파라미터 추가할것
		model.addAttribute("check", true);
		return "plan/planTimeRoom";
	}
	
	@GetMapping("/planBlocking")
	public String planUpdateRoom(Model model) {
		model.addAttribute("check", true);
		return "plan/planRoom";
	}

}
