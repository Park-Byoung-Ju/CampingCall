package com.example.demo.campingcall.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainBanner {
    private String contentid;
    
    private String firstimage;
    
    private String title;
    
    public String toInfo() {
		String result = "";
		result = "contentid : " + contentid + "\n"
				+ "firstimage : " + firstimage + "\n"
				+ "title : " + title;
		
		return result;
	}
}
