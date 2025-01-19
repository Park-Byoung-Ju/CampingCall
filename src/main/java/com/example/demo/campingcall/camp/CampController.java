package com.example.demo.campingcall.camp;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.camp.service.CampService;

@RequestMapping("/camp")
@Controller
public class CampController {
	
	private CampService campService;
	
	public CampController(CampService campService) {
		this.campService = campService;
	}

	@GetMapping("/campList")
	public String campList(@RequestParam(name="page", required=false) Integer page
						,Model model) {
		
		if(page == null) {
			page = 1;
		}
		
		List<Camp> campList = campService.getList(page);
		
		model.addAttribute("campList", campList);
		
		return "camp/campingList";
	}
	
	@GetMapping("/detail")
	public String campDetail(@RequestParam(name="keyword", required=false) String keyword
							,@RequestParam(name="contentsId", required=false) int contentId
							,Model model) {
		
		Camp camp = campService.getDetail(keyword);
		
		model.addAttribute("camp", camp);
		
		return "camp/detail";
	}
	
	@GetMapping("/search")
	public String campSearch() {
		return "camp/search";
	}
}
