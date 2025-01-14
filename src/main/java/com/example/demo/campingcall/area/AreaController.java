package com.example.demo.campingcall.area;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.campingcall.area.domain.Area;
import com.example.demo.campingcall.area.service.AreaService;

@Controller
public class AreaController {
	
	private AreaService areaService;
	
	public AreaController(AreaService areaService) {
		this.areaService = areaService;
	}

	@GetMapping("/area")
	public String getArea(Model model) {
		
		List<Area> areaList = new ArrayList<>();
		areaList = areaService.getAreaList();
		
		model.addAttribute("areaList", areaList);
		
		return "location/area";
	}
}
