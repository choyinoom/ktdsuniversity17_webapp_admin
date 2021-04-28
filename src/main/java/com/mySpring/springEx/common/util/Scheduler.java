package com.mySpring.springEx.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mySpring.springEx.course.dao.CourseDAO;

@Component
public class Scheduler{ 
	
	@Autowired
	CourseDAO courseDAO;
	
	/** * 1. ���� ���� 20:38:00�� ȣ���� �Ǵ� �����췯 */ 
	@Scheduled(cron = "00 38 20 * * *") 
	public void cronTest1(){ 
		System.out.println("���� 20:38:00�� ȣ���� �˴ϴ� ");
		int result = courseDAO.updateCourseStatusByDate();
		} 
	
	
	/** * 2. ���� 20:45:00�� ȣ���� �Ǵ� �����췯 */
	@Scheduled(cron = "00 45 20 * * *") 
	public void cronTest2(){ 
		System.out.println("���� 20:45:00�� ȣ���� �˴ϴ� ");
		int result = courseDAO.updateCourseStatusByNumOfApplicants();
		} 
	
//	/** * 3. ���� 20:45:00�� ȣ���� �Ǵ� �̰����� �����췯 */
//	@Scheduled(cron = "00 31 16 * * *") 
//	public void cronTest3(){ 
//		System.out.println("���� 16:31:00�� ȣ���� �˴ϴ� ");
//		int result = enrollmentDAO.updateCourseStatusByNumOfApplicants();
//		} 
}
