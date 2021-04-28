package com.mySpring.springEx.event_security_log.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mySpring.springEx.event_security_log.vo.Event_security_logVO;
import com.mySpring.springEx.survey.vo.SurveyVO;

public interface Event_security_logController {

	public ModelAndView listEvent_security_logs(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public int idCheckEvent_security_log(int id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView informationEvent_security_logForm(int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
	
}
