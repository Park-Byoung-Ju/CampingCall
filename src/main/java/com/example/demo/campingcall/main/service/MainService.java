package com.example.demo.campingcall.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.campingcall.api.ApiResponse;
import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.camp.service.CampService;
import com.example.demo.campingcall.common.WebClientManager;
import com.example.demo.campingcall.main.domain.MainBanner;
import com.example.demo.campingcall.main.domain.MainTrip;
import com.example.demo.campingcall.trip.service.TripService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class MainService {
	private TripService tripService;
	
	private CampService campService;
	
	public MainService(TripService tripService,
						CampService campService) {
		this.tripService = tripService;
		this.campService = campService;
	}
	
	public List<MainTrip> getMainPageTripList(){
		String baseUri = "https://apis.data.go.kr/B551011/KorService2/areaBasedList2";
			
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("pageNo", "1");
		map.add("contentTypeId", "12");
		map.add("MobileOS", "WIN");
		map.add("MobileApp", "TestApp");
		map.add("numOfRows", "3");	
		map.add("arrange", "Q");
		map.add("_type", "json");
		
		String uri = WebClientManager.setParamUri(baseUri, map);
		
		ApiResponse<List<MainTrip>> api = WebClientManager.getClient(uri);
		
		if(api == null) {
			return null;
		}
		
		List<MainTrip> result = new ArrayList<>();	
		
		try {
			result = WebClientManager.convertorData(api.getResponse().getBody().getItems().getItem(),MainTrip.class);			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return result;
	}
	
	public List<Camp> getMainPageCampList(){
		List<Camp> campList = new ArrayList<>();
		
		
		return campList;
	}
	
	public List<MainBanner> getMainBanner() {
		// 행사정보 uri로 변경하기
		String baseUri = "https://apis.data.go.kr/B551011/KorService2/searchFestival2";

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("pageNo", "1");
		map.add("MobileOS", "Web");
		map.add("MobileApp", "CampingCall");
		map.add("numOfRows", "10");	
		map.add("arrange", "Q");
		map.add("_type", "json");
		
		//행사 시작일 -> calendar를 사용할것
		map.add("eventStartDate", "20260201");
		
		String uri = WebClientManager.setParamUri(baseUri, map);
		
		ApiResponse<List<MainBanner>> api = WebClientManager.getClient(uri);
		
		if(api == null) {
			return null;
		}
		
		List<MainBanner> result = new ArrayList<>();	
		
		try {
			result = WebClientManager.convertorData(api.getResponse().getBody().getItems().getItem(),MainBanner.class);			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
