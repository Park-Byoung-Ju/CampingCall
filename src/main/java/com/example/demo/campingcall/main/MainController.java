package com.example.demo.campingcall.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


	@GetMapping("/main")
	public String main() {
		return "main/main";
	}
	
	@GetMapping("/test")
	public String test() {
		return "main/test";
	}
}
