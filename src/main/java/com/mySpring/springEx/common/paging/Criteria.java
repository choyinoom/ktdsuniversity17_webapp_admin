package com.mySpring.springEx.common.paging;

//리스트 개수에서 나누는 class
public class Criteria {

	private int page;
	private int perPageNum;
	private String searchType;
	private String searchText;

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page) {
		
		if(page <= 0) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPerPageNum(int perPageNum) {
		
		if (perPageNum <= 0 || perPageNum >100) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPerPageNum() {
		return this.perPageNum;
	}
	
	public int getPageStart() {
		return (this.page - 1) * perPageNum + 1;
	} 
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
