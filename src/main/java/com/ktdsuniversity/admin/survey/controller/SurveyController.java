package com.ktdsuniversity.admin.survey.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.survey.vo.SurveyVO;

public interface SurveyController {

	public ModelAndView listSurveys(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addSurvey(SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeSurvey(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public int removeCheckedSurveys(int[] arr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public int idCheckSurvey(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView updateSurvey(SurveyVO surveyVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView updateSurveyForm(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView informationSurveyForm(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	
	
}
