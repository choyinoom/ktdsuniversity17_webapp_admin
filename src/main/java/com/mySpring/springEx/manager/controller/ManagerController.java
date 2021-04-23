package com.mySpring.springEx.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.springEx.manager.vo.ManagerVO;

public interface ManagerController {

	public ModelAndView login(ManagerVO manager, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response ) throws Exception;

	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

	

	public ModelAndView updateManager(ManagerVO manager, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public ModelAndView updateManagerForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}
