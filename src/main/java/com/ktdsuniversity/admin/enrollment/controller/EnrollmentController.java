package com.ktdsuniversity.admin.enrollment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.enrollment.vo.EnrollmentVO;

public interface EnrollmentController {

	ModelAndView listEnrollments(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView informationEnrollment(int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView modEnrollment(EnrollmentVO enrollment, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView addEnrollment(EnrollmentVO enrollmentVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView enrollmentForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	int updateDeleteEnrollments(String [] arr, HttpServletRequest request, HttpServletResponse response) 
			throws Exception;

	int updateApproveEnrollments(String[] arr, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	int updateCompleteEnrollments(String[] arr, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView deleteEnrollment(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	void excelDownload(HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	ModelAndView enrollmentCourse(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView listCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	ModelAndView completionDoc(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	

	/*
	 * ModelAndView printCompletion(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception;
	 */

}