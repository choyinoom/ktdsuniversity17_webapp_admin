package com.mySpring.springEx.courseCategory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface CourseCategoryController {

	ModelAndView listAllCourseCategories(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
