package com.example.demo.campingcall.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.camp.service.CampService;
import com.example.demo.campingcall.main.domain.MainBanner;
import com.example.demo.campingcall.main.domain.MainTrip;
import com.example.demo.campingcall.main.service.MainService;

@Controller
public class MainController {
	
	private MainService mainService;
	
	private CampService campService;
	
	public MainController(MainService mainService
						, CampService campService) {
		this.mainService = mainService;
		this.campService= campService;
	}


	@GetMapping("/main")
	public String main(Model model) {
		List<MainTrip> tripList  =mainService.getMainPageTripList();
		List<Camp> campList = campService.getList(1, 3);
		List<MainBanner> bannerList = mainService.getMainBanner();
		
		/*
		//데이터 확인
		System.out.println();
		System.out.println("trip List");
		for(int i = 0; i < tripList.size(); i++) {
			System.out.println((i + 1) + "번째");
			System.out.println(tripList.get(i).toInfo());
			System.out.println();
		}
		System.out.println();
		
		System.out.println("Banner List");
		for(int i = 0; i < bannerList.size(); i++) {
			System.out.println((i + 1) + "번째");
			System.out.println(bannerList.get(i).toInfo());
			System.out.println();
		}
		
		System.out.println("CampList size : " + campList.size());
		*/
		
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("campList", campList);
		model.addAttribute("tripList", tripList);
		return "main/main";
	}
}
