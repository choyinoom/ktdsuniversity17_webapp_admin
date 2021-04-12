package com.mySpring.springEx.syllabus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mySpring.springEx.course.vo.CourseVO;
import com.mySpring.springEx.courseCategory.service.CourseCategoryService;
import com.mySpring.springEx.syllabus.service.SyllabusService;
import com.mySpring.springEx.syllabus.vo.SyllabusVO;

@Controller("syllabusController")
public class SyllabusControllerImpl implements SyllabusController{
	
	@Autowired
	private SyllabusService syllabusService;
	
	@Autowired
	CourseCategoryService courseCategoryService;
	
	@Autowired
	SyllabusVO syllabusVO;
	
	@Override
	@RequestMapping(value="/syllabus/listSyllabuses.do" ,method = RequestMethod.GET)
	public ModelAndView listSyllabuses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		List syllabusesList = syllabusService.listSyllabuses();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("syllabusesList", syllabusesList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/syllabus/addSyllabus.do" ,method = RequestMethod.POST)
	public ModelAndView addSyllabus(@ModelAttribute("syllabus") SyllabusVO syllabusVO,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = syllabusService.addSyllabus(syllabusVO);
		ModelAndView mav = new ModelAndView("redirect:/syllabus/listSyllabuses.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/syllabus/removeSyllabus.do" ,method = RequestMethod.GET)
	public ModelAndView removeSyllabus(@RequestParam("id") int id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		int result = syllabusService.removeSyllabus(id);
		ModelAndView mav = new ModelAndView("redirect:/syllabus/listSyllabuses.do");
		return mav;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/syllabus/removeCheckedSyllabuses.do", method = RequestMethod.POST)
	public int removeCheckedSyllabuses(String [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		System.out.println(arr.length);
		int result = 0;
		for(int i = 0; i < arr.length; i++) {
			System.out.println(Integer.parseInt(arr[i]));
			result = syllabusService.removeSyllabus(Integer.parseInt(arr[i]));
		}
		return result;
	}
	
	@Override
	@RequestMapping(value="/syllabus/updateSyllabusForm.do", method = RequestMethod.GET)
	public ModelAndView updateSyllabusForm(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		SyllabusVO syllabusVO = syllabusService.selectSyllabus(id);
		mv.addObject("syllabusVO", syllabusVO);
		List courseCategoryList = courseCategoryService.listAllCourseCategories();
		mv.addObject("courseCategoryList", courseCategoryList);
		return mv;
	}
	
	@Override
	@RequestMapping(value="/syllabus/updateSyllabus.do", method = RequestMethod.POST)
	public ModelAndView updateSyllabus(@ModelAttribute("syllabus") SyllabusVO syllabusVO,
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = syllabusService.updateSyllabus(syllabusVO);
		
		ModelAndView mav = new ModelAndView("redirect:/syllabus/listSyllabuses.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/syllabus/selectSyllabus.do", method = RequestMethod.GET)
	public ModelAndView selectSyllabus(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		SyllabusVO syllabusVO = syllabusService.selectSyllabus(id);
		mv.addObject("syllabusVO", syllabusVO);
		return mv;
	}
	
	
	
	@RequestMapping(value = "/syllabus/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(@RequestParam(value= "result", required=false) String result,
							  @RequestParam(value= "action", required=false) String action,
						       HttpServletRequest request, 
						       HttpServletResponse response) throws Exception {
		
		String viewName = (String)request.getAttribute("viewName");
		System.out.println(viewName);
		HttpSession session = request.getSession();
		session.setAttribute("action", action);  
		ModelAndView mav = new ModelAndView();
		List courseCategoryList = courseCategoryService.listAllCourseCategories();
		
		mav.addObject("result",result);
		mav.addObject("courseCategoryList", courseCategoryList);
		mav.setViewName(viewName);
		return mav;
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
