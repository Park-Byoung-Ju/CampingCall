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
	
	private String addr1;
	
	private String tel;
	
	private String telname;
	
	private String title;
	
	private String firstimage;
	
	private String firstimage2;
	
	private String areacode;
	
	private String sigungucode;
	
	private String mapx;
	
	private String mapy;
	
	private String mlevel;
	
	private String overview;
}
