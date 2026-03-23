package com.example.demo.campingcall.plan;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.campingcall.plan.domain.ResultResponse;
import com.example.demo.campingcall.plan.service.PlanService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/plan")
@RestController
public class PlanRestController {

	private PlanService planService;
	
	public PlanRestController(PlanService planService) {
		this.planService = planService;
	}
	
	@PostMapping("/create")
	public ResultResponse getCreateRoom(@RequestParam("title") String title
										,@RequestParam("startDate") LocalDate startDate
										,@RequestParam("endDate") LocalDate endDate
										,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		Integer roomId = planService.CreateRoom(userId, title, startDate, endDate);
		
		ResultResponse result = new ResultResponse();
		if(roomId != null) {		
			
			result.setResult(roomId);
			result.setTruecheck(true);
		}else {
			result.setTruecheck(false);
			result.setResult(roomId);
		}
		
		return result;
	}

}
