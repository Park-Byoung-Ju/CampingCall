package com.example.demo.campingcall.trip;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.campingcall.trip.domain.AreaBaseList;
import com.example.demo.campingcall.trip.domain.Item;
import com.example.demo.campingcall.trip.service.TripService;

@RequestMapping("/trip")
@Controller
public class TripController {
	
	private TripService tripService;
	
	public TripController(TripService tripService) {
		this.tripService = tripService;
	}
	
	@GetMapping("/tripList")
	public String tripList() {
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
	
	@ResponseBody
	@GetMapping("/apitest")
	public List<AreaBaseList> test(){
		List<AreaBaseList> list = tripService.getData();
		
		return list;
	}
}
