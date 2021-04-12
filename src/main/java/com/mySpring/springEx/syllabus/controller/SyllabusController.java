package com.mySpring.springEx.syllabus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mySpring.springEx.syllabus.vo.SyllabusVO;

public interface SyllabusController {

	public ModelAndView listSyllabuses(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addSyllabus(@ModelAttribute("syllabus") SyllabusVO syllabusVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeSyllabus(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView updateSyllabusForm(@RequestParam("id")int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView selectSyllabus(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView updateSyllabus(SyllabusVO syllabusVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	int removeCheckedSyllabuses(String[] arr, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
			

}
