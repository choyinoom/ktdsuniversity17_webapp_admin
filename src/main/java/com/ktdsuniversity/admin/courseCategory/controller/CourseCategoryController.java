package com.ktdsuniversity.admin.courseCategory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.courseCategory.vo.CourseCategoryVO;

public interface CourseCategoryController {

	ModelAndView listAllCourseCategories(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView addCourseCategory(CourseCategoryVO CourseCategoryVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView selectSyllabus(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView updateCourseCategory(CourseCategoryVO CourseCategoryVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView updateSyllabusForm(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView removeCourseCategory(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	int removeCheckedremoveCheckedCourseCategories(String[] arr, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
