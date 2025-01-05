package com.example.demo.campingcall.trip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/trip")
@Controller
public class TripController {
	
	@GetMapping("/tripList")
	public String tripList() {
		return "trip/tripList";
	}
}
