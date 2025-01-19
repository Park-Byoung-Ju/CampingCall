package com.example.demo.campingcall.camp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.camp.service.CampService;

@RequestMapping("/camp")
@RestController
public class CampRestController {
	
	private CampService campService;
	
	public CampRestController(CampService campService) {
		this.campService = campService;
	}
	
	@GetMapping("/pay")
	public boolean pay(@RequestParam("contentId") String contentId
					,@RequestParam("userId") int userId
					,@RequestParam("bookingNumber") int bookingNumber
					,@RequestParam("price") int price
					,@RequestParam("date") LocalDate date){
		
		
		
		return campService.setPay(Integer.parseInt(contentId), userId, bookingNumber, price, date);
	}
	
	@GetMapping("/payList")
	public Map<String, Object> getPayList(@RequestParam("contentId") String contentId
								,@RequestParam("date") LocalDate date) {
		//Integer.parseInt(contentId)
		List<Integer> payList = campService.getPayList(Integer.parseInt(contentId),date);
		
		Map<String, Object> resultMap = new HashMap<>();

		Camp camp = new Camp();
		camp.setBookingList(payList);
		resultMap.put("camp",camp);
		
		return resultMap;
	}

}
