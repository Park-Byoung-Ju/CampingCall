package com.example.demo.campingcall.trip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.campingcall.api.ApiResponse;
import com.example.demo.campingcall.common.WebClientManager;
import com.example.demo.campingcall.trip.domain.AreaBaseList;
import com.example.demo.campingcall.trip.domain.DetailCommon;
import com.example.demo.campingcall.trip.domain.DetailIntro;
import com.example.demo.campingcall.trip.domain.Trip;
import com.fasterxml.jackson.core.JsonProcessingException;


@Service
public class TripService {

	public List<AreaBaseList> getData(String pageNo) { // 관광지와 상세 데이터

		String baseUri = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("pageNo", pageNo);
		map.add("contentTypeId", "12");
		map.add("MobileOS", "WIN");
		map.add("MobileApp", "TestApp");
		map.add("_type", "json");
		
		String uri = WebClientManager.setParamUri(baseUri, map);
		
		ApiResponse<List<AreaBaseList>> api = WebClientManager.getClient(uri);
		
		List<AreaBaseList> result = new ArrayList<>();	
		
		try {
			result = WebClientManager.convertorData(api.getResponse().getBody().getItems().getItem(),AreaBaseList.class);
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public DetailCommon getDetailCommon(String contentId){
		String baseUri = "https://apis.data.go.kr/B551011/KorService1/detailCommon1";
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("MobileOS", "WIN");
		map.add("MobileApp", "CampingCall");
		map.add("contentId", contentId);
		map.add("contentTypeId", "12");
		map.add("defaultYN", "Y");
		map.add("areacodeYN", "Y");
		map.add("overviewYN", "Y");
		map.add("_type", "json");
	
		String uri = WebClientManager.setParamUri(baseUri, map);
		
		ApiResponse<List<DetailCommon>> apiDetailCommon = WebClientManager.getClient(uri);
		
		List<DetailCommon> detail = new ArrayList<>();
		
		try {
			detail = WebClientManager.convertorData(apiDetailCommon.getResponse().getBody().getItems().getItem(), DetailCommon.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return detail.get(0);
	}
	
	public DetailIntro getDetail(String contentId){
		String baseUri = "https://apis.data.go.kr/B551011/KorService1/detailIntro1";
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("numOfRows", "6");
		map.add("MobileOS", "WIN");
		map.add("MobileApp", "CampingCall");
		map.add("contentId", contentId);
		map.add("contentTypeId", "12");
		map.add("_type", "json");
	
		String uri = WebClientManager.setParamUri(baseUri, map);
		
		ApiResponse<List<DetailIntro>> apiDetail = WebClientManager.getClient(uri);
		
		List<DetailIntro> detail = new ArrayList<>();
		
		try {
			detail = WebClientManager.convertorData(apiDetail.getResponse().getBody().getItems().getItem(), DetailIntro.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return detail.get(0);
	}
	
	public List<Trip> getTripList(int pageNo) {
		List<AreaBaseList> areaBaseList = getData(String.valueOf(pageNo));
		
		List<Trip> tripList = new ArrayList<>();
		for(int i = 0; i < areaBaseList.size(); i++) {
			String contentId = areaBaseList.get(i).getContentid();
			
			AreaBaseList areaBaseListItem = areaBaseList.get(i);
			DetailIntro detailIntro = getDetail(contentId);
			DetailCommon detailCommon = getDetailCommon(contentId);
			
			Trip trip = new Trip();
			trip.setAreaBaseList(areaBaseListItem);
			trip.setDetailIntro(detailIntro);
			trip.setDetailCommon(detailCommon);
			
			tripList.add(trip);
		}
		
		return tripList;
	}
	
	public Trip getTrip(int pageNo) {
		List<AreaBaseList> areaBaseList = getData(String.valueOf(pageNo));
		
		String contentId = areaBaseList.get(0).getContentid();
		
		AreaBaseList areaBaseListItem = areaBaseList.get(0);
		DetailIntro detailIntro = getDetail(contentId);
		
		Trip trip = new Trip();
		trip.setAreaBaseList(areaBaseListItem);
		trip.setDetailIntro(detailIntro);

		
		return trip;
	}
}
