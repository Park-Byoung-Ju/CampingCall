package com.example.demo.campingcall.trip.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaBaseList {
// 지역기반 관광 정보 조회	
	private String contentid;
	
	private String addr1;
	
	private String areacode;
	
	private String cat3;
	
	private String contenttypeid;
	
	private String firstimage;
	
	private String firstimage2;
	
	private String cpyrhtDivCd;
	
	private String mapx;
	
	private String mapy;
	
	private String mlevel;
	
	private String sigungucode;
	
	private String tel;
	
	private String title;
	
	private String zipcode;

}
