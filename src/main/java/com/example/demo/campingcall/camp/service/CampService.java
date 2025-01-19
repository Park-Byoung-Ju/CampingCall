package com.example.demo.campingcall.camp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.campingcall.api.ApiResponse;
import com.example.demo.campingcall.camp.domain.Camp;
import com.example.demo.campingcall.camp.domain.CampBooking;
import com.example.demo.campingcall.camp.repository.CampRepository;
import com.example.demo.campingcall.common.WebClientManager;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CampService {
	
	private CampRepository campRepository;
	
	public CampService(CampRepository campRepository) {
		this.campRepository = campRepository;
	}

	// 리스트 가져오기
	public List<Camp> getList(int page){
		List<Camp> campList = new ArrayList<>();
		String baseUri = "http://apis.data.go.kr/B551011/GoCamping/basedList";
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("pageNo", String.valueOf(page));
		map.add("numOfRows", "10");
		map.add("MobileOS", "WIN");
		map.add("MobileApp", "TestApp");
		map.add("_type", "json");
		
		String uri = WebClientManager.setParamUri(baseUri, map);
		
		ApiResponse<List<Camp>> api = WebClientManager.getClient(uri);
		
		try {
			campList = WebClientManager.convertorData(api.getResponse().getBody().getItems().getItem(), Camp.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		campList.get(0).setAllCount(api.getResponse().getBody().getTotalCount());
		
		return campList;	
	}
	
	// 리스트 가져오기 끝
	
	// 댓글 리스트 가져오기
	// 댓글 리스트 가져오기 끝
	
	// 상세정보 가져오기
	public Camp getDetail(String keyword) {
		
		String baseUri = "http://apis.data.go.kr/B551011/GoCamping/searchList";
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("keyword", keyword);
		map.add("MobileOS", "WIN");
		map.add("MobileApp", "TestApp");
		map.add("_type", "json");
		
		String uri = WebClientManager.setParamUri(baseUri, map);
		
		ApiResponse<List<Camp>> api = WebClientManager.getClient(uri);
		
		List<Camp> campList = new ArrayList<>();
		try {
			campList = WebClientManager.convertorData(api.getResponse().getBody().getItems().getItem(), Camp.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return campList.get(0);
	}
	// 상세정보 가져오기 끝
	
	// 결제정보
	public boolean setPay(int contentId
			,int userId
			,int bookingNumber
			,int price
			,LocalDate date) {
		
		CampBooking booking = CampBooking.builder()
										.contentId(contentId)
										.userId(userId)
										.bookingNumber(bookingNumber)
										.price(price)
										.date(date)
										.build();
		
		CampBooking result = campRepository.save(booking);
		
		if(result == null) {
			return false;
		}else {
			return true;
		}	
	}
	
	// 결제정보 끝
	
	// 결제 정보 가져오기
	public List<Integer> getPayList(int contentId, LocalDate date) {
		List<CampBooking> bookingList = campRepository.findByContentIdAndDateOrderByBookingNumber(contentId, date);
		
		List<Integer> resultList = new ArrayList<>();
		if(bookingList.size() == 0) {
			resultList.add(0);
			return resultList;
		}

		for(int i = 0; i < bookingList.size(); i++) {	
			
			resultList.add(bookingList.get(i).getBookingNumber());
		}
		
		return resultList;
	}
	// 결제 정보 가져오기 끝
}
