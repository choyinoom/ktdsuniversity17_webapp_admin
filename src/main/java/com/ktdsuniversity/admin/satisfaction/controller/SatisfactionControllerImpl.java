package com.ktdsuniversity.admin.satisfaction.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import com.ktdsuniversity.admin.satisfaction.service.SatisfactionService;
import com.ktdsuniversity.admin.satisfaction.vo.SatisfactionVO;
import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.common.paging.PageMaker;
import com.ktdsuniversity.admin.course.service.CourseService;
import com.ktdsuniversity.admin.course.vo.CourseVO;




@Controller("satisfactionController")
public class SatisfactionControllerImpl implements SatisfactionController{
	
	@Autowired
	private SatisfactionService satisfactionService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	SatisfactionVO satisfactionVO;
	
	@Autowired
	CourseVO courseVO;
	
	@Override
	@RequestMapping(value="/satisfaction/listSatisfactions.do" ,method = RequestMethod.GET)
	public ModelAndView listSatisfactions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");

		// page 기본 변수 생성
		int page = 0;
				
		// 받은 페이지 값이 있다면
		if(request.getParameter("page") != null) {
			page = Integer.parseInt((String)request.getParameter("page")); //page 변수에 값을 저장
		} else {
			page = 1; //아니면 page 1로 기본 지정
		}
				
		int perPage = 0; //리스트 개수 값 저장할 변수 생성
				
		// perPage 값이 있다면
		if(request.getParameter("perPage") != null) {
			perPage = Integer.parseInt((String)request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		} else {
			perPage = 10; // 기본 10개로 지정
		}
		System.out.println("전달 받은 페이지 번호 page:"+page); // 전달 받은 페이지 번호 page 
		System.out.println("리스트 띄울 개수 Perpage:"+perPage); // 전달 받은 페이지 번호 page 
				
		List satisfactionsList = null; 
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();
				
		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 기준 값 설정
				
		if (searchType != null && searchText != null) { // 검색 유형이랑 값을 받았다면
			System.out.println(searchType);
			System.out.println(searchText);
			System.out.println("@@@@@@@@@검색필터 적용됨요");
			satisfactionsList = satisfactionService.listBySearchSatisfactions(searchType, searchText);
			System.out.println("@@@@@@@@@@서치된 리스트"+satisfactionsList.size()); // 검색해서 받은 전체 리스트 사이즈
			criteria.setPage(page); // page 설정
			criteria.setSearchText(searchText); // 검색 값 설정
			criteria.setSearchType(searchType); // 검색 유형 설정
			pageMaker.setTotalCount(satisfactionsList.size()); // 페이지 개수를 전체 리스트 크기로 설정
			satisfactionsList = satisfactionService.listCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
			System.out.println("page@@@"+page+"번호에 해당하는 리스트 크기"+satisfactionsList.size());
					
			mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내기
			mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
		} else { // 검색 유형이랑 값을 받지 않았다면
			System.out.println("@@@@@@@@검색필터적용안됨요");
			satisfactionsList = satisfactionService.listSatisfactions(); //전체 리스트 저장
			System.out.println(satisfactionsList.size()); //전체 사이즈
			criteria.setPage(page); //페이지 설정
			pageMaker.setTotalCount(satisfactionsList.size()); //페이지 개수 설정
			satisfactionsList = satisfactionService.listCriteria(criteria); //기준에 의해 나눠진 리스트 설정
		}
		System.out.println("perPage@@@@@@@@@@@@"+perPage);
		mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
		mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
		mav.addObject("satisfactionsList", satisfactionsList); //설정된 리스트 보내기
		return mav; //리스트 페이지로
	}
	
	
	//기준에 의해 나눠진 리스트 확인해보며 연습할 페이지로 -> 실제 구현할 때는 필요없을 예정
		@RequestMapping(value = "/satisfaction/listCriteria.do", method = RequestMethod.GET)
		public String listCriteria(Model model, Criteria criteria) throws Exception {
			model.addAttribute("satisfactions", satisfactionService.listCriteria(criteria));
			return "/satisfaction/list_criteria";
			
		} 
		
		//페이지 개수가 어떻게 나눠지는지 확인해보며 연습할 페이지로 -> 실제 구현할 때는 필요없을 예정
		@RequestMapping(value = "/satisfaction/listPaging.do", method = RequestMethod.GET)
		public String listPaging(Model model, Criteria criteria) throws Exception {
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(1000);
			
			model.addAttribute("satisfactions", satisfactionService.listCriteria(criteria));
			model.addAttribute("pageMaker", pageMaker);
			return "/satisfaction/list_paging";
		}
	
	
	

	@Override
	@RequestMapping(value="/satisfaction/addSatisfaction.do" ,method = RequestMethod.POST)
	public ModelAndView addSatisfaction(@ModelAttribute("satisfaction") SatisfactionVO satisfactionVO,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = satisfactionService.addSatisfaction(satisfactionVO);
		ModelAndView mav = new ModelAndView("redirect:/satisfaction/listSatisfactions.do");
		return mav;
	}
	
	
	@Override
	@ResponseBody
	@RequestMapping(value="/satisfaction/idCheckSatisfaction.do", method = RequestMethod.POST)
	public int idCheckSatisfaction(@RequestParam("id") int id, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		System.out.println(viewName);
		System.out.println(id);
		int result = satisfactionService.idCheckSatisfaction(id);
		System.out.println(result);
		return result;
	}
	
	
	@Override
	@RequestMapping(value="/satisfaction/removeSatisfaction.do" ,method = RequestMethod.GET)
	public ModelAndView removeSatisfaction(@RequestParam("id") int id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		satisfactionService.removeSatisfaction(id);
		ModelAndView mav = new ModelAndView("redirect:/satisfaction/listSatisfactions.do");
		return mav;
	}
	
	@Override
	@ResponseBody
	@RequestMapping(value="/satisfaction/removeCheckedSatisfactions.do", method = RequestMethod.POST)
	public int removeCheckedSatisfactions(int [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		System.out.println(arr.length);
		int result = 0;
		for(int i = 0; i < arr.length; i++) {
			result = satisfactionService.removeSatisfaction(arr[i]);
		}
		return result;
	}
	
	
	@Override
	@RequestMapping(value="/satisfaction/modSatisfaction.do", method = RequestMethod.POST)
	public ModelAndView updateSatisfaction(@ModelAttribute("satisfaction") SatisfactionVO satisfactionVO,
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = satisfactionService.updateSatisfaction(satisfactionVO);
		ModelAndView mav = new ModelAndView("redirect:/satisfaction/listSatisfactions.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/satisfaction/modSatisfactionForm.do", method = RequestMethod.GET)
	public ModelAndView updateSatisfactionForm(@RequestParam int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		SatisfactionVO vo = satisfactionService.selectSatisfaction(id);
//		CourseVO courseVO = courseService.selectCourse(id);
		mv.addObject("vo", vo);
//		mv.addObject("courseVO", courseVO);
		return mv;
	}
	
	@Override
	@RequestMapping(value="/satisfaction/informationSatisfactionForm.do", method = RequestMethod.GET)
	public ModelAndView informationSatisfactionForm(@RequestParam int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		SatisfactionVO vo = satisfactionService.selectSatisfaction(id);
		mv.addObject("vo", vo);
		return mv;
	}
	
	
	
	@RequestMapping(value = "/satisfaction/satisfactionForm.do", method =  RequestMethod.GET)
	private ModelAndView satisfactionForm(@RequestParam(value= "result", required=false) String result,
							  @RequestParam(value= "action", required=false) String action,
						       HttpServletRequest request, 
						       HttpServletResponse response) throws Exception {
		
		String viewName = (String)request.getAttribute("viewName");
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
