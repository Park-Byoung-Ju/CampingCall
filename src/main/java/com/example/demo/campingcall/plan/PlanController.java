package com.example.demo.campingcall.plan;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.campingcall.plan.domain.Room;
import com.example.demo.campingcall.plan.service.PlanService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/plan")
@Controller
public class PlanController {
	
	private PlanService planService;
	
	public PlanController(PlanService planService) {
		this.planService = planService;
	}
	
	@GetMapping("/main")
	public String planMain(HttpServletRequest request
						,Model model) {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		List<Room> roomList = planService.getRoomList(userId);
		
		model.addAttribute("roomList", roomList);
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
