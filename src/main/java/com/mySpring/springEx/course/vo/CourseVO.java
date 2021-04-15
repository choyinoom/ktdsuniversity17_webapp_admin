package com.mySpring.springEx.course.vo;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.mySpring.springEx.syllabus.vo.SyllabusVO;

@Component("CourseVO")
public class CourseVO {

	private int id;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private int capacity;
	private String classroom;
	private String joinDate;
	private String bannerImg;
	private int slbId;
	private String rsDate;
	private String reDate;
	
	private SyllabusVO syllabusVO;
	
	public CourseVO() {
	}

	public CourseVO(int id, String startDate, String endDate, String startTime, String endTime, int capacity,
			String classroom, String joinDate, String bannerImg, int slbId, SyllabusVO syllabusVO, String rsDate,
			String reDate) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
		this.classroom = classroom;
		this.joinDate = joinDate;
		this.bannerImg = bannerImg;
		this.slbId = slbId;
		this.syllabusVO = syllabusVO;
		this.rsDate = rsDate;
		this.reDate = reDate;
	}



	public String getRsDate() {
		return rsDate;
	}

	public void setRsDate(String rsData) {
		this.rsDate = rsData;
	}

	public String getReDate() {
		return reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getClassroom() {
		return classroom;
	}
	
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	public String getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	public String getBannerImg() {
		return bannerImg;
	}
	
	public void setBannerImg(String bannerImg) {
		try {
			if(bannerImg!= null && bannerImg.length()!=0) {
				this.bannerImg = URLEncoder.encode(bannerImg,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public int getSlbId() {
		return slbId;
	}
	
	public void setSlbId(int slbId) {
		this.slbId = slbId;
	}

	public SyllabusVO getSyllabusVO() {
		return syllabusVO;
	}

	public void setSyllabusVO(SyllabusVO syllabusVO) {
		this.syllabusVO = syllabusVO;
	}
	
	
	
	
}
