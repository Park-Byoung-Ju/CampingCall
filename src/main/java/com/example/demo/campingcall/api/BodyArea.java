package com.example.demo.campingcall.api;

public class BodyArea<T> {
	private Items<T> items;
	
	private int numOfRows;
	
    private int pageNo;
    
    private int totalCount;
    
	public Items<T> getItems() {
		return items;
	}

	public void setItems(Items<T> items) {
		this.items = items;
	}

	public int getNumOfRows() {
		return numOfRows;
	}

	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
