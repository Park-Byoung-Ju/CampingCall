package com.example.demo.campingcall.trip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.campingcall.api.ApiResponse;
import com.example.demo.campingcall.common.WebClientManager;
import com.example.demo.campingcall.trip.domain.AreaBaseList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class TripService {

	public List<AreaBaseList> getData() { // 관광지와 상세 데이터

		String baseUri = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("numOfRows", "10");
		map.add("pageNo", "1");
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

}
