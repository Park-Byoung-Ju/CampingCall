package com.example.demo.campingcall.trip.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaBaseList {
// 지역기반 관광 정보 조회	
	private int allCount;
	
	private String contentid;
	
	private String areacode;
	
	private String cat3;
	
	private String contenttypeid;
	
	private String cpyrhtDivCd;
	
	private String zipcode;

}
