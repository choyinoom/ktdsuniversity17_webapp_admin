package com.ktdsuniversity.admin.enrollment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.common.paging.PageMaker;
import com.ktdsuniversity.admin.course.service.CourseService;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.enrollment.service.EnrollmentService;
import com.ktdsuniversity.admin.enrollment.vo.EnrollmentVO;
import com.ktdsuniversity.admin.member.service.MemberService;
import com.ktdsuniversity.admin.member.vo.MemberVO;

@Controller("enrollmentController")
public class EnrollmentControllerImpl implements EnrollmentController{

	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberVO memberVO;
	
	@Autowired
	CourseVO courseVO;
	
	//excel download
	@Override
	@ResponseBody
	@RequestMapping(value="/enrollment/excelDownload.do", method = RequestMethod.GET)
	public void excelDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String log = (String)request.getParameter("log");
		int excelDownload = enrollmentService.logExcelDownload(log);
		
		enrollmentService.excelDownload(response);
	}
	
	// list page
	@Override
	@RequestMapping(value="/enrollment/listEnrollments.do", method =  RequestMethod.GET)
	public ModelAndView listEnrollments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//EnrollmentList에서 보낸 name 받는다. (searchType : 검색 유형, searchText : 검색 텍스트)
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
	
		int page = 0;		// page 기본 변수 생성
		if(request.getParameter("page") != null) { // 받은 페이지 값이 있다면
			page = Integer.parseInt((String)request.getParameter("page")); //page 변수에 값을 저장
		} else {
			page = 1; //아니면 page 1로 기본 지정
		}
		
		int perPage = 0; //리스트 개수 값 저장할 변수 생성
		if(request.getParameter("perPage") != null) {	// perPage 값이 있다면
			perPage = Integer.parseInt((String)request.getParameter("perPage")); // perPage 변수에 리스트 띄울 개수 저장
		} else {
			perPage = 10; // 기본 10개로 지정
		}
		
		List enrollmentsList = null; 
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();
		
		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 전달 받은 페이지 번호 page 
		
		if (searchType != null && searchText != null) { // 검색 유형이랑 값을 받았다면
			enrollmentsList = enrollmentService.listBySearchEnrollments(searchType, searchText);
			criteria.setPage(page); // page 설정
			criteria.setSearchText(searchText); // 검색 값 설정
			criteria.setSearchType(searchType); // 검색 유형 설정
			pageMaker.setTotalCount(enrollmentsList.size()); // 페이지 개수를 전체 리스트 크기로 설정
			enrollmentsList = enrollmentService.listEnrollmentCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
			mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내기
			mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
		} else { // 검색 유형이랑 값을 받지 않았다면
			enrollmentsList = enrollmentService.listEnrollments(); //전체 리스트 저장
			criteria.setPage(page); //페이지 설정
			pageMaker.setTotalCount(enrollmentsList.size()); //페이지 개수 설정
			enrollmentsList = enrollmentService.listEnrollmentCriteria(criteria); //기준에 의해 나눠진 리스트 설정
		}
		mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
		mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
		mav.addObject("enrollmentsList", enrollmentsList); //설정된 리스트 보내기
		return mav; //리스트 페이지로
	}

	//add enrollment
	@Override
	@RequestMapping(value="/enrollment/addEnrollment" ,method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView addEnrollment(@ModelAttribute("enrollment") EnrollmentVO enrollmentVO,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		System.out.println(enrollmentVO.getMemberVO().getId());
		result = enrollmentService.addEnrollment(enrollmentVO);
		ModelAndView mav = new ModelAndView("redirect:/enrollment/listEnrollments.do");
		return mav;
	}
	
	// modify - detail page
	@Override
	@RequestMapping(value="/enrollment/modEnrollment.do" ,method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView modEnrollment(@ModelAttribute("enrollment") EnrollmentVO enrollment,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = enrollmentService.modEnrollment(enrollment);
		ModelAndView mav = new ModelAndView("redirect:/enrollment/listEnrollments.do");
		return mav;
	}

	// move - add enrollment page
	@Override
	@RequestMapping(value = "/enrollment/enrollmentForm.do", method = RequestMethod.GET)
	public ModelAndView enrollmentForm(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		List syllabusesCoursesList = enrollmentService.listSylCrs();
		mav.addObject("syllabusesCoursesList", syllabusesCoursesList);
		return mav;
	}
	
	// Into detail page --> member
	@Override
	@RequestMapping(value="/enrollment/informationEnrollment.do" ,method = RequestMethod.GET)
	public ModelAndView informationEnrollment(@RequestParam int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		EnrollmentVO enrollmentVO = enrollmentService.selectEnrollment(id);	
		mv.addObject("enrollmentVO", enrollmentVO);
		return mv;
	}
	
	// Into detail page --> course
	@Override
	@RequestMapping(value="/enrollment/enrollmentCourse.do" ,method = RequestMethod.GET)
	public ModelAndView enrollmentCourse(@RequestParam int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		List enrollmentsList = null;
		enrollmentsList = enrollmentService.enrollmentCourse(id);	
		ModelAndView mav = new ModelAndView();
		mav.addObject("enrollmentsList", enrollmentsList);
		return mav;
	}
	
	// some modify - complete
	@Override
	@ResponseBody
	@RequestMapping(value="/enrollment/modCompleteEnrollments.do", method = RequestMethod.POST)
	public int updateCompleteEnrollments(String [] arr, 
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		for(int i = 0; i < arr.length; i++) { 
			result = enrollmentService.updateCompleteEnrollments(Integer.parseInt(arr[i]));
		} 
		return result;
	}

	// some modify - delete
	@Override
	@ResponseBody
	@RequestMapping(value="/enrollment/modDeleteEnrollments.do", method = RequestMethod.POST)
	public int updateDeleteEnrollments(String [] arr, 
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		for(int i = 0; i < arr.length; i++) { 
			result = enrollmentService.updateDeleteEnrollments(Integer.parseInt(arr[i]));
		} 
		return result;
	}
	
	// some modify - approve
	@Override
	@ResponseBody
	@RequestMapping(value="/enrollment/modApproveEnrollments.do", method = RequestMethod.POST)
	public int updateApproveEnrollments(String [] arr, 
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		for(int i = 0; i < arr.length; i++) { 
			result = enrollmentService.updateApproveEnrollments(Integer.parseInt(arr[i]));
		}
		return result;
	}
	
	// one delete
	@Override
	@RequestMapping(value="/enrollment/deleteEnrollment.do" ,method = RequestMethod.GET)
	public ModelAndView deleteEnrollment(@RequestParam String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = enrollmentService.updateDeleteEnrollments(Integer.parseInt(id));	
		ModelAndView mav = new ModelAndView("redirect:/enrollment/listEnrollments.do");
		return mav;
	}
	
	//수료관리 리스트 출력 메소드
	@Override
	@RequestMapping(value="/enrollment/listCompletion.do" ,method = RequestMethod.GET)
	public ModelAndView listCompletion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchText);
		
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
				
				List completionList = null; 
				ModelAndView mav = new ModelAndView(viewName);
				Criteria criteria = new Criteria();
				PageMaker pageMaker = new PageMaker();
				
				
				criteria.setPerPageNum(perPage); // 리스트 개수 설정
				pageMaker.setCriteria(criteria); // 기준 값 설정
				
				if (searchType != null && searchText != null) { // 검색 유형이랑 값을 받았다면
					System.out.println(searchType);
					System.out.println(searchText);
					System.out.println("@@@@@@@@@검색필터 적용됨요");
					completionList = enrollmentService.listBySearchCompletion(searchType, searchText);
					System.out.println("@@@@@@@@@@서치된 리스트"+completionList.size()); // 검색해서 받은 전체 리스트 사이즈
					criteria.setPage(page); // page 설정
					criteria.setSearchText(searchText); // 검색 값 설정
					criteria.setSearchType(searchType); // 검색 유형 설정
					pageMaker.setTotalCount(completionList.size()); // 페이지 개수를 전체 리스트 크기로 설정
					completionList = enrollmentService.listCompletionCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
					System.out.println("page@@@"+page+"번호에 해당하는 리스트 크기"+completionList.size());
					
					mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내기
					mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
				} else { // 검색 유형이랑 값을 받지 않았다면
					System.out.println("@@@@@@@@검색필터적용안됨요");
					completionList = enrollmentService.listCompletion(); //전체 리스트 저장
//					membersList = memberService.listCriteria(criteria);
					
					 System.out.println(completionList.size()); //전체 사이즈 
					 criteria.setPage(page); //페이지 설정 
					 pageMaker.setTotalCount(completionList.size()); //페이지 개수 설정
					 completionList = enrollmentService.listCompletionCriteria(criteria); //기준에 의해 나눠진 리스트 설정
					 			}
				System.out.println("perPage@@@@@@@@@@@@"+perPage);
				mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
				mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
				mav.addObject("completionList", completionList); //설정된 리스트 보내기
				return mav; //리스트 페이지로
			}
	
	//수료증 출력 메소드
	@Override
	@RequestMapping(value="/enrollment/completionDoc.do", method = RequestMethod.GET)
	public ModelAndView completionDoc(@RequestParam int id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		EnrollmentVO enrollmentVO = enrollmentService.selectCompletion(id);
		mv.addObject("enrollmentVO", enrollmentVO);
		return mv;
	}
	
	/*
	 * //수료증 출력 메소드2
	 * 
	 * @RequestMapping(value = "/enrollment/printCompletion.do", method =
	 * RequestMethod.GET)
	 * 
	 * @Override public ModelAndView printCompletion(HttpServletRequest request,
	 * HttpServletResponse response) throws Exception { String viewName =
	 * (String)request.getAttribute("viewName"); System.out.println(viewName);
	 * ModelAndView mav = new ModelAndView(); mav.setViewName(viewName); return mav;
	 * }
	 */
	
}