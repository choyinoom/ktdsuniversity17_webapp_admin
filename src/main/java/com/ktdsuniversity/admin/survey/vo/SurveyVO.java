package com.ktdsuniversity.admin.survey.vo;

import java.io.UnsupportedEncodingException;
import org.springframework.stereotype.Component;


@Component("SurveyVO")
public class SurveyVO {

	private int id;
	private String title;
	private String joinDate;
	private String state;
	private String contents;
	private String url;
	
	
	
	public SurveyVO() {
	}

	public SurveyVO(int id, String title, String joinDate, String state, String contents, String url) {
		this.id = id;
		this.title = title;
		this.joinDate = joinDate;
		this.state = state;
		this.contents = contents;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	


	
	
}
