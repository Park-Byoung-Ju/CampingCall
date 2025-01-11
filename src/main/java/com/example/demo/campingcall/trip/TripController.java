package com.example.demo.campingcall.trip;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.campingcall.trip.domain.Trip;
import com.example.demo.campingcall.trip.service.TripService;

@RequestMapping("/trip")
@Controller
public class TripController {
	
	private TripService tripService;
	
	public TripController(TripService tripService) {
		this.tripService = tripService;
	}
	
	@GetMapping("/tripList")
	public String tripList(@RequestParam(name="page", required=false) Integer page
						,Model model) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		page--;
		
		List<Trip> tripList = tripService.getTripList(page);
		
		model.addAttribute("tripList", tripList);
		
		return "trip/tripList";
	}
	
	@GetMapping("/detail")
	public String tripDetail() {
		return "trip/detail";
	}
	
	@GetMapping("/search")
	public String tripSearch() {
		return "trip/search";
	}
}
