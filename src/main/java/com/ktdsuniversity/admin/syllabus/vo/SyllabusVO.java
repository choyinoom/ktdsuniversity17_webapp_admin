package com.ktdsuniversity.admin.syllabus.vo;


import org.springframework.stereotype.Component;

import com.ktdsuniversity.admin.courseCategory.vo.CourseCategoryVO;

@Component("syllabusVO")
public class SyllabusVO {
	
	private int id;
	private String name;
	private String reportName;
	private String type;
	private String category;
	private String overview;
	private String objectives;
	private String target;
	private String contents;
	private String joinDate;
	private String modDate;
	private String bannerImg;
	private int days;
	private int time;
	
	private CourseCategoryVO courseCategoryVO;
	
	
	public SyllabusVO() {
	}
	
	
	public SyllabusVO(int id, String name, String reportName, String type, String category, String overview,
			String objectives, String target, String contents, String joinDate, String modDate, String bannerImg,int days, int time,
			CourseCategoryVO courseCategoryVO) {
		this.id = id;
		this.name = name;
		this.reportName = reportName;
		this.type = type;
		this.category = category;
		this.overview = overview;
		this.objectives = objectives;
		this.target = target;
		this.contents = contents;
		this.joinDate = joinDate;
		this.modDate = modDate;
		this.bannerImg = bannerImg;
		this.days = days;
		this.time = time;
		this.courseCategoryVO = courseCategoryVO;
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
	
	public String getReportName() {
		return reportName;
	}
	
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getOverview() {
		return overview;
	}
	
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	public String getObjectives() {
		return objectives;
	}
	
	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	public String getModDate() {
		return modDate;
	}
	
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	
	public int getDays() {
		return days;
	}
	
	public void setDays(int days) {
		this.days = days;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}


	public CourseCategoryVO getCourseCategoryVO() {
		return courseCategoryVO;
	}


	public void setCourseCategoryVO(CourseCategoryVO courseCategoryVO) {
		this.courseCategoryVO = courseCategoryVO;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}
}
