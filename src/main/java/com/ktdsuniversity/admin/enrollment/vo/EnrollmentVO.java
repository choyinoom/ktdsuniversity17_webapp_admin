package com.ktdsuniversity.admin.enrollment.vo;

import org.springframework.stereotype.Component;

import com.ktdsuniversity.admin.company.vo.CompanyVO;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.member.vo.MemberVO;
import com.ktdsuniversity.admin.syllabus.vo.SyllabusVO;

@Component("enrollmentVO")
public class EnrollmentVO {
	
	private int id;
	private int crsId;
	private String memId;
	private String slbName;
	private String stat;
	private String joinDate;
	private String completeDate;
	private String modDate;
	
	private MemberVO memberVO;
	private CourseVO courseVO;
	private SyllabusVO syllabusVO;
	private CompanyVO companyVO;
	
	public EnrollmentVO() {
	}

	public EnrollmentVO(int id, int crsId, String memId, String slbName, String stat, String joinDate, String modDate, String completeDate,
			MemberVO memberVO, CourseVO courseVO, SyllabusVO syllabusVO, CompanyVO companyVO) {
		this.id = id;
		this.crsId = crsId;
		this.memId = memId;
		this.slbName = slbName;
		this.stat = stat;
		this.joinDate = joinDate;
		this.modDate = modDate;
		this.completeDate = completeDate;
		this.memberVO = memberVO;
		this.courseVO = courseVO;
		this.syllabusVO = syllabusVO;
		this.companyVO = companyVO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCrsId() {
		return crsId;
	}

	public void setCrsId(int crsId) {
		this.crsId = crsId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getSlbName() {
		return slbName;
	}

	public void setSlbName(String slbName) {
		this.slbName = slbName;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
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
	
	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public CourseVO getCourseVO() {
		return courseVO;
	}

	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}

	public SyllabusVO getSyllabusVO() {
		return syllabusVO;
	}

	public void setSyllabusVO(SyllabusVO syllabusVO) {
		this.syllabusVO = syllabusVO;
	}

	public CompanyVO getCompanyVO() {
		return companyVO;
	}

	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
	
}
