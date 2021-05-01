package com.ktdsuniversity.admin.course.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.common.util.UploadDataVO;
import com.ktdsuniversity.admin.course.vo.CourseVO;

public interface CourseController {
	
	public ModelAndView listCourses(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addCourse(@ModelAttribute("course")CourseVO courseVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeCourse(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView updateCourseForm(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView updateCourse(@ModelAttribute("course")CourseVO courseVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView selectCourse(@ModelAttribute("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView courseForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	int updateCheckedCourses(String[] arr, String stat, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	int removeCheckedCourses(String[] arr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	int checkClassRoomOfCourses(HashMap<String, String> param, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
