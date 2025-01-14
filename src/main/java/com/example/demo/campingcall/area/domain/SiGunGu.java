package com.example.demo.campingcall.area.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiGunGu {

	private String code;
	
	private String name;
	
	public SiGunGu(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
