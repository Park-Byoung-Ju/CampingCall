package com.example.demo.campingcall.common;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging{

	// 필수 멤버변수
	// 전체 데이터 개수
	private int allCount;
	
	// 전체 페이지 개수
	private int allPage;
	
	// 반환 해줄 데이터
	private List<Integer> pageList = new ArrayList<>();
	// 필수 멤버변수 끝
	
	// 메소드
	public Paging(int allCount) {
		this.allCount = allCount;
	}
	
	// size = 데이터를 1페이지에 묶을 개수	
	// group = 한번에 보일 페이지 개수
	// page = 현재 페이지
	public List<Integer> getPagingList(int size, int group, int page){
		
		if((Integer)size == null || (Integer)group == null || (Integer)page == null) {
			return null;
		}
		
		if(size <= 0 || group <= 0 || page <= 0) {
			return null;
		}
		
		if(allCount % size == 0) {
			this.allPage = allCount / size;
		}else {
			this.allPage = allCount / size + 1;
		}
		
		// 현재 page가 group으로 나누어 떨어지지 않으면 나누어 떨어질 때까지 --; 실행
		page--;
		
		while(page % group != 0) {
			page--;
		}
		
		page++;
		
		// page가 group으로 나누어 떨어졌다면 반복문으로 list에 데이터 삽입
		// list에 group 개수만큼 데이터를 삽입
		for(int i = 0; i < group; i++) {
			if(page > allPage) {
				break;
			}
			this.pageList.add(page++);
		}

		return this.pageList;
	}
	// 메소드 끝
}
