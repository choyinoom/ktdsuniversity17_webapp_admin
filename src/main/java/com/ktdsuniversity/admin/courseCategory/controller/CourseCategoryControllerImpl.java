package com.ktdsuniversity.admin.courseCategory.controller;

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

import com.ktdsuniversity.admin.courseCategory.service.CourseCategoryService;
import com.ktdsuniversity.admin.courseCategory.vo.CourseCategoryVO;
import com.ktdsuniversity.admin.syllabus.vo.SyllabusVO;

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
	
	@Override
	@RequestMapping(value="/courseCategory/addCourseCategory.do" ,method = RequestMethod.POST)
	public ModelAndView addCourseCategory(@ModelAttribute("courseCategory") CourseCategoryVO CourseCategoryVO,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = courseCategoryService.addCourseCategory(CourseCategoryVO);
		ModelAndView mav = new ModelAndView("redirect:/courseCategory/listAllCourseCategories.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/courseCategory/removeCourseCategory.do" ,method = RequestMethod.GET)
	public ModelAndView removeCourseCategory(@RequestParam("id") int id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		int result = courseCategoryService.removeCourseCategory(id);
		ModelAndView mav = new ModelAndView("redirect:/courseCategory/listAllCourseCategories.do");
		return mav;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/courseCategory/removeCheckedCourseCategories.do", method = RequestMethod.POST)
	public int removeCheckedremoveCheckedCourseCategories(String [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		System.out.println(arr.length);
		int result = 0;
		for(int i = 0; i < arr.length; i++) {
			System.out.println(Integer.parseInt(arr[i]));
			result = courseCategoryService.removeCourseCategory(Integer.parseInt(arr[i]));
		}
		return result;
	}
	
	@Override
	@RequestMapping(value="/courseCategory/selectCourseCategory.do", method = RequestMethod.GET)
	public ModelAndView selectSyllabus(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		CourseCategoryVO courseCategoryVO = courseCategoryService.selectCourseCategory(id);
		mv.addObject("courseCategoryVO", courseCategoryVO);
		return mv;
	}
	
	@Override
	@RequestMapping(value="/courseCategory/updateCourseCategoryForm.do", method = RequestMethod.GET)
	public ModelAndView updateSyllabusForm(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		CourseCategoryVO courseCategoryVO = courseCategoryService.selectCourseCategory(id);
		mv.addObject("courseCategoryVO", courseCategoryVO);
		mv.setViewName(viewName);
		return mv;
	}
	
	@Override
	@RequestMapping(value="/courseCategory/updateCourseCategory.do", method = RequestMethod.POST)
	public ModelAndView updateCourseCategory(@ModelAttribute("courseCategory") CourseCategoryVO CourseCategoryVO,
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = courseCategoryService.updateCourseCategory(CourseCategoryVO);
		ModelAndView mav = new ModelAndView("redirect:/courseCategory/listAllCourseCategories.do");
		return mav;
	}
	
	@RequestMapping(value = "/courseCategory/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(@RequestParam(value= "result", required=false) String result,
							  @RequestParam(value= "action", required=false) String action,
						       HttpServletRequest request, 
						       HttpServletResponse response) throws Exception {
		
		String viewName = (String)request.getAttribute("viewName");
		System.out.println(viewName);
		HttpSession session = request.getSession();
		session.setAttribute("action", action);  
		ModelAndView mav = new ModelAndView();		
		mav.addObject("result",result);
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
