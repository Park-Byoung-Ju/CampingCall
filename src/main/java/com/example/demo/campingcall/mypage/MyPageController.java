package com.example.demo.campingcall.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mypage")
@Controller
public class MyPageController {
	
	
	@GetMapping("/payList")
	public String payList() {
		return "pay/payList";
	}

}
