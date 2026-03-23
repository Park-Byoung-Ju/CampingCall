package com.example.demo.campingcall.plan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//API에서 불러온 데이터를 DB에 넣는 DTO
@Builder(toBuilder=true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "content")
@Entity
public class Content {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="contentId")
	private int contentId;
	
	@Column(name="contentTitle")
	private String contentTitle;
	
	@Column(name="contentImgUrl")
	private String contentImgUrl;
}
