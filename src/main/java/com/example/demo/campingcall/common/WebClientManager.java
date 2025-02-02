package com.example.demo.campingcall.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.campingcall.api.ApiResponse;
import com.example.demo.campingcall.trip.domain.AreaBaseList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WebClientManager {
	
	// encoding key
	//public final static String KEY= "LEoRzNehyS3bpMxZp6vlzEMYTKR9epu5kIxxesG2T9L0DEOYz1korA86TalB4gAHGTffD3mHdZoaB9%2FRNzmQ4g%3D%3D";	
	
	// decoding key
	public final static String KEY = "LEoRzNehyS3bpMxZp6vlzEMYTKR9epu5kIxxesG2T9L0DEOYz1korA86TalB4gAHGTffD3mHdZoaB9/RNzmQ4g==";
	
	public static WebClient create() {
		return WebClient.builder()
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
	                .build();
	}
	
	//ParameterizedTypeReference<T> responseType
	public static <T> ApiResponse<T> getClient(String uri) {
		WebClient webClient = create();
		

		try {
			ApiResponse<T> result =  webClient
					.get()
					.uri(uri)
					.header("Accept",MediaType.APPLICATION_JSON_VALUE)
					.retrieve()
					.bodyToMono(ApiResponse.class)
					.block();
			
			return result;
		}catch(Exception e) {
			return null;
		}	
	}

	public static String setParamUri(String url, MultiValueMap<String, String> paramMap) {
		
		return UriComponentsBuilder
				.fromUriString(url)
				.queryParams(paramMap)
				.build()
				.toUriString();
	}
	
	// 원래는 웹클라이언트에서 파싱을 해야하지만 손수 변환
	public static <T> List<T> convertorData(List<T> list, Class<T> type) throws JsonProcessingException{
		if(list == null) {
			return null;
		}
		
		List<T> result = new ArrayList<>();
				
		for(int i = 0; i < list.size(); i++) {
			ObjectMapper objectMapper = new ObjectMapper();

			String jsonString = objectMapper.writeValueAsString(list.get(i));

			T t = (T) objectMapper.readValue(jsonString, type);
			
			result.add(t);
		}

		return result;
	}

}
