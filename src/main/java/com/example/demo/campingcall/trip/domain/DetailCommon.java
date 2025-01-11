package com.example.demo.campingcall.trip.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailCommon {

	private String contentid;
	
	private String contenttypeid;
	
	private String homepage;
	
	private String tel;
	
	private String telname;
	
	private String title;
	
	private String areacode;
	
	private String sigungucode;
	
	private String overview;
}
