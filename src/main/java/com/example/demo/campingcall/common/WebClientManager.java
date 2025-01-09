package com.example.demo.campingcall.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.campingcall.api.ApiResponse;
import com.example.demo.campingcall.api.Item;
import com.example.demo.campingcall.api.Items;


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
	public static <T> ApiResponse getClient(String uri) {
		WebClient webClient = create();


		return webClient
				.get()
				.uri(uri)
				.header("Accept",MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(ApiResponse.class)
				.block();
										
	}
	
	public static WebClient postClient(String uri) {
		WebClient webClient = create();

		webClient.post()
                .uri(uri);
				
		return webClient;
	}
	
	public static String setParamUri(String url, MultiValueMap<String, String> paramMap) {
		
		return UriComponentsBuilder
				.fromUriString(url)
				.queryParams(paramMap)
				.build()
				.toUriString();
	}
	
	public static <T> Object start(WebClient.RequestHeadersSpec<?> webClient, ParameterizedTypeReference<T> responseType) {
		/* 원리는 제대로 모르겠지만 WebClient.RequestHeadersSpec<?> 에서 retrieve()가 실행되는데
		 * webclient에서 build 하는 과정에서는 WebClient.RequestHeadersSpec<?>가 같이 실행되기 때문에
		 * 
		*/
		
		// List 타입은 new ParameterizedTypeReference<List<T>>() 로 생성해서 넘길것
		return  webClient
                .retrieve()
                .bodyToMono(responseType)
                .block();
	}
}
