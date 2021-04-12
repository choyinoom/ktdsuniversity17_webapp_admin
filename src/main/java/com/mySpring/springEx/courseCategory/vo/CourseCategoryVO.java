package com.mySpring.springEx.courseCategory.vo;

import org.springframework.stereotype.Component;

@Component("CourseCategoryVO")
public class CourseCategoryVO {
	
	private int id;
	private String name;
	
	public CourseCategoryVO() {
	}
	
	public CourseCategoryVO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
