package com.mySpring.springEx.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mySpring.springEx.company.service.CompanyService;
import com.mySpring.springEx.company.vo.CompanyVO;

@Controller("companyController")
public class CompanyControllerImpl implements CompanyController{

	@Autowired
	CompanyService companyService;
	
	@Override
	@RequestMapping(value="/company/listCompanies.do" ,method = RequestMethod.GET)
	public ModelAndView listCompanies(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		List companiesList = companyService.listCompanies();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("companiesList", companiesList);
		return mav;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/company/listBySearch.do" ,method = RequestMethod.POST)
	public List listBySearchCompanies(@ModelAttribute("company") CompanyVO companyVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		List companiesBySearchList = companyService.listBySearchCompanies(companyVO);
		return companiesBySearchList;
	}
	
	@Override
	@RequestMapping(value="/company/selectCompany.do", method = RequestMethod.GET)
	public ModelAndView selectCompany(@RequestParam("comId") String comId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		CompanyVO companyVO = companyService.selectCompany(comId);
		mv.addObject("companyVO", companyVO);
		return mv;
	}
	
	@RequestMapping(value = "/company/popUp.do", method = {RequestMethod.GET, RequestMethod.POST})
	private ModelAndView popUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		System.out.println(viewName);
		List companiesList = companyService.listCompanies();
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("companiesList", companiesList);
		return mv;
	}
	
	
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
}
