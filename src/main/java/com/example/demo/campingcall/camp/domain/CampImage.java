package com.example.demo.campingcall.camp.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampImage {
	// content primary key
	private String contentId;
	// 이미지 primary key
	private String serialnum;
	// 이미지 url
	private String imageUrl;
}
