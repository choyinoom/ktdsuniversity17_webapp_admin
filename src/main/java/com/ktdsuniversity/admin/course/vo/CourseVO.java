package com.ktdsuniversity.admin.course.vo;


import org.springframework.stereotype.Component;

import com.ktdsuniversity.admin.courseCategory.vo.CourseCategoryVO;
import com.ktdsuniversity.admin.syllabus.vo.SyllabusVO;

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
	private String rsDate;
	private String reDate;
	private String stat;
	private int applyNum;
	private int slbId;
	private String crsrsdate;
	private String crsredate;
	
	
	private SyllabusVO syllabusVO;
	private CourseCategoryVO courseCategoryVO;
	
	public CourseVO() {
	}

	public CourseVO(int id, String startDate, String endDate, String startTime, String endTime, int capacity,
			String classroom, String joinDate, String rsDate, String reDate, String stat, int applyNum, int slbId,
			SyllabusVO syllabusVO, CourseCategoryVO courseCategoryVO) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
		this.classroom = classroom;
		this.joinDate = joinDate;
		this.rsDate = rsDate;
		this.reDate = reDate;
		this.stat = stat;
		this.applyNum = applyNum;
		this.slbId = slbId;
		this.rsDate = rsDate;
		this.reDate = reDate;
		this.syllabusVO = syllabusVO;
		this.courseCategoryVO = courseCategoryVO;
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

	public String getRsDate() {
		return rsDate;
	}

	public void setRsDate(String rsDate) {
		this.rsDate = rsDate;
	}

	public String getReDate() {
		return reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public int getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
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

	public String getCrsrsdate() {
		return crsrsdate;
	}

	public void setCrsrsdate(String crsrsdate) {
		this.crsrsdate = crsrsdate;
	}

	public String getCrsredate() {
		return crsredate;
	}

	public void setCrsredate(String crsredate) {
		this.crsredate = crsredate;
	}
	
	public CourseCategoryVO getCourseCategoryVO() {
		return courseCategoryVO;
	}

	public void setCourseCategoryVO(CourseCategoryVO courseCategoryVO) {
		this.courseCategoryVO = courseCategoryVO;
	}

}
