package com.example.demo.campingcall.area.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.campingcall.area.domain.Area;
import com.example.demo.campingcall.area.domain.AreaCode;
import com.example.demo.campingcall.area.domain.SiGunGuCode;

@Service
public class AreaService {

	
	public List<Area> getAreaList(){
		List<Area> areaList = new ArrayList<>();
		areaList = AreaCode.getList();
		
		for(Area area : areaList) {
			area.setSiGunGuList(SiGunGuCode.getList(area.getAreaCode()));
		}
		
		return areaList;
	}
}
