package com.ktdsuniversity.admin.satisfaction.vo;

import org.springframework.stereotype.Component;

import com.ktdsuniversity.admin.course.vo.CourseVO;



@Component("SatisfactionVO")
public class SatisfactionVO {

	private int id;
	private String title;
	private String joinDate;
	private String state;
	private String contents;
	private String url;
	private int crsId;
	
	private CourseVO courseVO;
	
	public SatisfactionVO() {
	}

	public SatisfactionVO(int id, String title, String joinDate, String state, String contents, String url, int crsId) {
		this.id = id;
		this.title = title;
		this.joinDate = joinDate;
		this.state = state;
		this.contents = contents;
		this.url = url;
		this.crsId = crsId;
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

	public int getCrsId() {
		return crsId;
	}

	public void setCrsId(int crsId) {
		this.crsId = crsId;
	}

	public CourseVO getCourseVO() {
		return courseVO;
	}

	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}
	
	
}
