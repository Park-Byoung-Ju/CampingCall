package com.example.demo.campingcall.board.domain;

import java.util.Set;

public class Paging {
	
	private int page;
	
	private int allCount;
	
	private int endGroup;

	private int first;
	
	private int second;
	
	private int third;
	
	private int fourth;
	
	private int fifth;
	
// 실행 메소드
	public void setPaging() {
		int count = boardService.countByAll(); // 데이터 총 개수
		int avg = count / 7 + 1;
		int mypage = (page - 1) / 5;
		
		int first = mypage * 5 + 1;
		int second = mypage * 5 + 2;
		int third = mypage * 5 + 3;
		int fourth = mypage * 5 + 4;
		int fifth = mypage * 5 + 5;
		
		resultPaging.put("first", first);
		resultPaging.put("second", second);
		resultPaging.put("third", third);
		resultPaging.put("fourth", fourth);
		resultPaging.put("fifth", fifth);
		
		Set<String> keySet = resultPaging.keySet();
		
		for(String key : keySet) {
			int sum = resultPaging.get(key);
			
			if(sum <= avg) {
				continue;
			}else {
				resultPaging.put(key, null);
			}
		}
		
		int endGroup = avg;
		
		while(!(endGroup % 5 == 0)) {
			endGroup++;
		}
		
		endGroup /= 5;
	}


	
// first second third fourth fifth 의 getter와 setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getEndGroup() {
		return endGroup;
	}

	public void setEndGroup(int endGroup) {
		this.endGroup = endGroup;
	}

	public int getFirst() {
		return first;
	}
/*
	public void setFirst(int first) {
		this.first = first;
	}
*/
	public int getSecond() {
		return second;
	}
/*
	public void setSecond(int second) {
		this.second = second;
	}
*/
	public int getThird() {
		return third;
	}
/*
	public void setThird(int third) {
		this.third = third;
	}
*/
	public int getFourth() {
		return fourth;
	}
/*
	public void setFourth(int fourth) {
		this.fourth = fourth;
	}
*/
	public int getFifth() {
		return fifth;
	}
/*
	public void setFifth(int fifth) {
		this.fifth = fifth;
	}
*/	
//	
}
