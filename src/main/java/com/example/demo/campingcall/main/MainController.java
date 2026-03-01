package com.example.demo.campingcall.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.main.domain.MainBanner;
import com.example.demo.campingcall.main.domain.MainTrip;
import com.example.demo.campingcall.main.service.MainService;

@Controller
public class MainController {
	
	private MainService mainService;
	
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}


	@GetMapping("/main")
	public String main(Model model) {
		List<MainTrip> tripList  =mainService.getMainPageTripList();
		List<Camp> campList = mainService.getMainPageCampList();
		List<MainBanner> bannerList = mainService.getMainBanner();
		
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("campList", campList);
		model.addAttribute("tripList", tripList);
		return "main/main";
	}
}
