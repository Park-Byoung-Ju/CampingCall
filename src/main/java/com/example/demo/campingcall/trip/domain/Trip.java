package com.example.demo.campingcall.trip.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Trip {
	
	private AreaBaseList areaBaseList;
	
	private DetailIntro detailIntro;
	
	private DetailCommon detailCommon;
}
