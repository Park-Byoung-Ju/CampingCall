package com.example.demo.campingcall.main.domain;

import java.util.List;

import com.example.demo.campingcall.camp.domain.CampImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainCamp {
	// 이미지 리스트
	private List<CampImage> imageList;
	// primary key
	private String contentId;
	// 야영장명
	private String facltNm;
	// 주소
	private String addr1;
}
