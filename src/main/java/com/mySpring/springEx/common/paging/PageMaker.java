package com.mySpring.springEx.common.paging;

//페이징 개수 정하는 class
public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Criteria criteria;
	
	
	public PageMaker() {
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;

	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));
		
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = (startPage == 1) ? false : true;
		
		next = (endPage * criteria.getPerPageNum() >= totalCount) ? false : true;
	}
	
	/* 
	 if (page == startPage ) { 
	 	impArticlesList = boardService.listImptArticles();
	 	
	 		 }
	 else {
	 	articlesList = boardService.listArticles();
	 	
	 		 }
	  
	 */

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria getCriteria() {
		return criteria;
	}
	
	
}
