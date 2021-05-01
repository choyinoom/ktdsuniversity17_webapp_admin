package com.ktdsuniversity.admin.course.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.common.paging.PageMaker;
import com.ktdsuniversity.admin.course.service.CourseService;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.syllabus.service.SyllabusService;
import com.ktdsuniversity.admin.syllabus.vo.SyllabusVO;

@Controller("courseController")
public class CourseControllerImpl implements CourseController{
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private SyllabusService syllabusService;
	
	@Autowired
	CourseVO courseVO;
	
	@Autowired
	SyllabusVO syllabusVO;
	
	@Override
	@RequestMapping(value="/course/listCourses.do" ,method = RequestMethod.GET)
	public ModelAndView listCourses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		
		int page = 0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt((String)request.getParameter("page"));
		} else {
			page = 1;
		}
		
		int perPage = 0;
		if (request.getParameter("perPage") != null) {
			perPage = Integer.parseInt((String)request.getParameter("perPage"));
		} else {
			perPage = 10;
		}
		
		List coursesList = null;
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();
		
		criteria.setPerPageNum(perPage);
		pageMaker.setCriteria(criteria);
		
		if (searchType != null && searchText != null) {
			coursesList = courseService.listBySearchCourses(searchType, searchText);
			criteria.setPage(page);
			criteria.setSearchText(searchText);
			criteria.setSearchType(searchType);
			pageMaker.setTotalCount(coursesList.size());
			coursesList = courseService.listCriteriaBySearch(criteria);
			
			mav.addObject("searchText", searchText);
			mav.addObject("searchType", searchType);
		} else {
			coursesList = courseService.listCourses();
			criteria.setPage(page);
			pageMaker.setTotalCount(coursesList.size());
			coursesList = courseService.listCriteria(criteria);
		}
		
		mav.addObject("page", page);
		mav.addObject("perPage", perPage);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("coursesList", coursesList);
		mav.addObject("viewName", viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/course/addCourse.do" ,method = RequestMethod.POST)
	public ModelAndView addCourse(@ModelAttribute("course") CourseVO courseVO,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String searchType = (String)request.getParameter("searchType");
		String searchText = URLEncoder.encode((String)request.getParameter("searchText"),"UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		int result = 0;
		result = courseService.addCourse(courseVO);
		ModelAndView mav = new ModelAndView("redirect:/course/listCourses.do?page="+page+"&searchText="+searchText+"&searchType="+searchType+"&perPage="+perPage);
		return mav; 
		
	}
	
	@Override
	@RequestMapping(value="/course/removeCourse.do" ,method = RequestMethod.GET)
	public ModelAndView removeCourse(@RequestParam("id") int id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String searchType = (String)request.getParameter("searchType");
		String searchText = URLEncoder.encode((String)request.getParameter("searchText"),"UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		courseService.removeCourse(id);
		ModelAndView mav = new ModelAndView("redirect:/course/listCourses.do?page="+page+"&searchText="+searchText+"&searchType="+searchType+"&perPage="+perPage);
		return mav;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/course/removeCheckedCourses.do", method = RequestMethod.POST)
	public int removeCheckedCourses(String [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		int result = 0;
		for(int i = 0; i < arr.length; i++) {
			result = courseService.removeCourse(Integer.parseInt(arr[i]));
		}
		return result;
	}
	
	
	@Override
	@RequestMapping(value="/course/courseForm.do", method = RequestMethod.GET)
	public ModelAndView courseForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		List syllabusesList = syllabusService.listSyllabuses();
		mv.addObject("searchType", searchType);
		mv.addObject("searchText", searchText);
		mv.addObject("page", page);
		mv.addObject("perPage", perPage);
		mv.addObject("syllabusesList", syllabusesList);
		return mv;
	}
	
	@Override
	@RequestMapping(value="/course/updateCourseForm.do", method = RequestMethod.GET)
	public ModelAndView updateCourseForm(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		ModelAndView mv = new ModelAndView();
		CourseVO courseVO = courseService.selectCourse(id);
		mv.setViewName(viewName);
		mv.addObject("searchType", searchType);
		mv.addObject("searchText", searchText);
		mv.addObject("page", page);
		mv.addObject("perPage", perPage);
		mv.addObject("courseVO", courseVO);
		return mv;
	}
	
	@Override
	@RequestMapping(value="/course/updateCourse.do", method = RequestMethod.POST)
	public ModelAndView updateCourse(@ModelAttribute("course") CourseVO courseVO,
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String searchType = (String)request.getParameter("searchType");
		String searchText = URLEncoder.encode((String)request.getParameter("searchText"),"UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		int result = 0;
		
		result = courseService.updateCourse(courseVO);
		ModelAndView mav = new ModelAndView("redirect:/course/selectCourse.do?id="+courseVO.getId()+"&page="+page+"&searchText="+searchText+"&searchType="+searchType+"&perPage="+perPage);
		return mav;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/course/updateCheckedCourses.do", method = RequestMethod.POST)
	public int updateCheckedCourses(String [] arr, String stat, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap();
		int result = 0;
		String updateStat = stat;
		
		if (updateStat.equals("applicable") ) {
			System.out.println("stat의 값이 신청가능으로 바뀜");
			updateStat = "신청가능";
		} else if (updateStat.equals("earlyClosing")) {
			System.out.println("stat의 값이 조기마감으로 바뀜");
			updateStat = "조기마감";
		} else {
			System.out.println("stat의 값이 마감으로 바뀜");
			updateStat = "마감";
		}
		
		map.put("stat", updateStat);
		for (int i = 0; i < arr.length; i++) {
			int id = Integer.parseInt(arr[i]);
			map.put("id", id);
			result = courseService.updateByButtonCourse(map);
		}
		
		return result;
	}
	
	@Override
	@RequestMapping(value="/course/selectCourse.do", method = RequestMethod.GET)
	public ModelAndView selectCourse(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		int page = Integer.parseInt(request.getParameter("page"));
		int perPage = Integer.parseInt(request.getParameter("perPage"));
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		CourseVO courseVO = courseService.selectCourse(id);
		mv.addObject("courseVO", courseVO);
		mv.addObject("searchType", searchType);
		mv.addObject("searchText", searchText);
		mv.addObject("page", page);
		mv.addObject("perPage", perPage);
		return mv;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/course/checkClassRoomOfCourses.do", method = RequestMethod.POST)
	public int checkClassRoomOfCourses(@RequestBody HashMap<String, String> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = param.get("id");
		int result = 0;
		if (id != null) {
			result = courseService.checkClassRoomOfCoursesById(param);
		} else {
			result = courseService.checkClassRoomOfCourses(param);
		}
		 
		System.out.println("@@@@@@@@@@반환값"+result);
		return result;
	}
	
//	@RequestMapping(value = "/course/*Form.do", method =  RequestMethod.GET)
//	private ModelAndView form(@RequestParam(value= "result", required=false) String result,
//							  @RequestParam(value= "action", required=false) String action,
//						       HttpServletRequest request, 
//						       HttpServletResponse response) throws Exception {
//		
//		String viewName = (String)request.getAttribute("viewName");
//		HttpSession session = request.getSession();
//		session.setAttribute("action", action);  
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("result",result);
//		mav.setViewName(viewName);
//		return mav;
//	}
	
	
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
