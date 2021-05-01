package com.ktdsuniversity.admin.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ktdsuniversity.admin.course.dao.CourseDAO;
import com.ktdsuniversity.admin.enrollment.dao.EnrollmentDAO;

@Component
public class Scheduler{ 
	
	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	EnrollmentDAO enrollmentDAO;
	
	/** * 1. 오후 20:38:00에 호출이 되는 스케쥴러 */ 
	@Scheduled(cron = "00 38 20 * * *") 
	public void cronTest1(){ 
		System.out.println("오후 20:38:00에 호출이 됩니다 ");
		int result = courseDAO.updateCourseStatusByDate();
		} 
	
	
	/** * 2. 오후 20:45:00에 호출이 되는 스케쥴러 */
	@Scheduled(cron = "00 45 20 * * *") 
	public void cronTest2(){ 
		System.out.println("오후 20:45:00에 호출이 됩니다 ");
		int result = courseDAO.updateCourseStatusByNumOfApplicants();
		} 
	
	// 강좌 종료 날짜 지나면 수료대기로 자동변경
	/* 3. 00:00:05에 호출이 되는 스케쥴러 */
	@Scheduled(cron = "05 00 00 * * *") 
	public void cronTest3(){ 
		System.out.println("오전 00:00:05에 호출이 됩니다 ");
		int result = enrollmentDAO.updateEnrollmentStatusByDate();
		} 
}
