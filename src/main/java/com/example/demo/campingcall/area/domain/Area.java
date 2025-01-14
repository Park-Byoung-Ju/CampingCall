package com.example.demo.campingcall.area.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Area {
	String areaCode;
	
	String name;
	
	List<SiGunGu> siGunGuList;
	
	public Area(String name, String areaCode) {
		this.areaCode = areaCode;
		this.name = name;
	}
}
