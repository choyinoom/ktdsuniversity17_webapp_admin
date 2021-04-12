package com.mySpring.springEx.courseCategory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mySpring.springEx.courseCategory.service.CourseCategoryService;
import com.mySpring.springEx.courseCategory.vo.CourseCategoryVO;

@Controller("courseCategorycontroller")
public class CourseCategoryControllerImpl implements CourseCategoryController{

	@Autowired
	CourseCategoryService courseCategoryService;
	
	@Override
	@RequestMapping(value="/courseCategory/listAllCourseCategories.do" ,method = RequestMethod.GET)
	public ModelAndView listAllCourseCategories(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		List courseCategoryList = courseCategoryService.listAllCourseCategories();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("courseCategoryList", courseCategoryList);
		return mav;
	}
}
