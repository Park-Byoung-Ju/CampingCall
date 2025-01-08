package com.example.demo.campingcall.common;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.util.UriComponentsBuilder;

public class WebClientManager {
	
	private final static String KEY= "LEoRzNehyS3bpMxZp6vlzEMYTKR9epu5kIxxesG2T9L0DEOYz1korA86TalB4gAHGTffD3mHdZoaB9%2FRNzmQ4g%3D%3D";	
	
	public static WebClient create() {
		return WebClient.create();
	}
	
	public static WebClient getClient(String uri) {
		WebClient webClient = create();

		webClient.get()
                .uri(uri);
				
		return webClient;
										
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
