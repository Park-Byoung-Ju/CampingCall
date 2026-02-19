package com.example.demo.campingcall.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainTrip {
	private String addr1;
	
	private String contentid;
	
	private String firstimage;
	
	private String title;
	
	public String toInfo() {
		String result = "";
		result = "addr1 : " + addr1 + "\n"
				+ "contentid : " + contentid + "\n"
				+ "firstimage : " + firstimage + "\n"
				+ "title : " + title;
		
		return result;
	}
}
