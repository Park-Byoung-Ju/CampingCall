package com.example.demo.campingcall.trip;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.campingcall.api.AreaCode1;
import com.example.demo.campingcall.common.WebClientManager;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/trip")
@Controller
public class TripController {
	
	@GetMapping("/tripList")
	public String tripList() {
		return "trip/tripList";
	}
	
	@GetMapping("/detail")
	public String tripDetail() {
		return "trip/detail";
	}
	
	@GetMapping("/search")
	public String tripSearch() {
		return "trip/search";
	}
	
	@ResponseBody
	@GetMapping("/api")
	public AreaCode1 test(HttpServletResponse response) {

		String baseUri = "http://apis.data.go.kr/B551011/KorService1/areaCode1";
		//String baseUri = "http://apis.data.go.kr/B551011/KorService1/locationBasedList1";
		String uriuri = "http://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=LEoRzNehyS3bpMxZp6vlzEMYTKR9epu5kIxxesG2T9L0DEOYz1korA86TalB4gAHGTffD3mHdZoaB9%2FRNzmQ4g%3D%3D&numOfRows=1&pageNo=1&MobileOS=ETC&MobileApp=TestApp&_type=json";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("serviceKey", WebClientManager.KEY);
		map.add("numOfRows", "17");
		map.add("MobileOS", "WIN");
		map.add("MobileApp", "TestApp");
		map.add("_type", "json");
		
		String uri = WebClientManager.setParamUri(baseUri, map);
		
		Test test = new Test();
		//test.setSomeField("1");
		
		return WebClient.create()
				.get()
				.uri(uri)
				.header("Accept",MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<AreaCode1>() {})
				.block();
		 
		
		//return test;
	}
	
	@GetMapping("/test")
	public String getTest() {
		return "trip/test";
	}
	/*	
	@ResponseBody
	@GetMapping("/test2")
	public List<Map<String,Object>> getGap(){
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("name", "한라산");
			resultMap.put("good", "제주감귤");
			resultMap.put("price", "1000원");		
			resultList.add(resultMap);
		}
		return resultList;
	}
	
	@ResponseBody
	@GetMapping("/test3")
	public BodyArea getApiData(){
		Items items = new Items();
		items.setItem(WebClient.create()
				.get()
				.uri("http://localhost:8080/trip/test2")
				.header("Accept",MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Map<String,Object>>>() {})
				.block());
		
		items.setItem1(WebClient.create()
				.get()
				.uri("http://localhost:8080/trip/test2")
				.header("Accept",MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Item>>() {})
				.block());
		
		BodyArea bodyArea = new BodyArea();
		bodyArea.setItems(items);
		
		return bodyArea;
	}
	*/
}
