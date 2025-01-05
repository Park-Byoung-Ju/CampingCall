package com.example.demo.campingcall.camp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/camp")
@Controller
public class CampController {

	@GetMapping("/campList")
	public String tripList() {
		return "camp/campingList";
	}
}
