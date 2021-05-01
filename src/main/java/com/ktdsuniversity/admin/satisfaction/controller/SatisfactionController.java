package com.ktdsuniversity.admin.satisfaction.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.satisfaction.vo.SatisfactionVO;
import com.ktdsuniversity.admin.survey.vo.SurveyVO;

public interface SatisfactionController {

	public ModelAndView listSatisfactions(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView addSatisfaction(SatisfactionVO satisfactionVO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public int idCheckSatisfaction(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView removeSatisfaction(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public int removeCheckedSatisfactions(int[] arr, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView updateSatisfaction(SatisfactionVO satisfactionVO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView updateSatisfactionForm(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView informationSatisfactionForm(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	
	
	
}
