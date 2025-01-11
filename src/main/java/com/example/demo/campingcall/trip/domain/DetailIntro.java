package com.example.demo.campingcall.trip.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailIntro {
// 관광지 소개 정보
	// id
	private String contentid;
	
	private String contenttypeid;
	
	private String chkpet;
	
	private String expagerange;
	
	private String expguide;
	
	private String heritage1;
	
	private String infocenter;
	
	private String parking;
	
	private String restdate;
	
	private String useseason;
	
	private String usetime;

}
