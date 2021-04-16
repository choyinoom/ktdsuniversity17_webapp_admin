package com.mySpring.springEx.enrollment.controller;

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

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.common.paging.PageMaker;
import com.mySpring.springEx.course.service.CourseService;
import com.mySpring.springEx.course.vo.CourseVO;
import com.mySpring.springEx.enrollment.service.EnrollmentService;
import com.mySpring.springEx.enrollment.vo.EnrollmentVO;
import com.mySpring.springEx.member.service.MemberService;
import com.mySpring.springEx.member.vo.MemberVO;

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

	//기준에 의해 나눠진 리스트 확인해보며 연습할 페이지로 -> 실제 구현할 때는 필요없을 예정
	@RequestMapping(value = "/member/listCriteria.do", method = RequestMethod.GET)
	public String listCriteria(Model model, Criteria criteria) throws Exception {
		model.addAttribute("members", memberService.listCriteria(criteria));
		return "/member/list_criteria";
		
	} 
	
	//페이지 개수가 어떻게 나눠지는지 확인해보며 연습할 페이지로 -> 실제 구현할 때는 필요없을 예정
	@RequestMapping(value = "/member/listPaging.do", method = RequestMethod.GET)
	public String listPaging(Model model, Criteria criteria) throws Exception {
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(1000);
		
		model.addAttribute("members", memberService.listCriteria(criteria));
		model.addAttribute("pageMaker", pageMaker);
		return "/member/list_paging";
		
	}	
	
//////////////////////////////////////////////////////////////////////	
	
	@Override
	@RequestMapping(value="/enrollment/listEnrollments.do", method =  RequestMethod.GET)
	public ModelAndView listEnrollments(HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		//mebmerList에서 보낸 name 받는다. (searchType : 검색 유형, searchText : 검색 텍스트)
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
		
		List enrollmentsList = null; 
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();
		
		
		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 기준 값 설정
		
		if (searchType != null && searchText != null) { // 검색 유형이랑 값을 받았다면
			System.out.println(searchType);
			System.out.println(searchText);
			System.out.println("@@@@@@@@@검색필터 적용됨요");
			enrollmentsList = enrollmentService.listBySearchEnrollments(searchType, searchText);
			
			System.out.println("@@@@@@@@@@서치된 리스트"+enrollmentsList.size()); // 검색해서 받은 전체 리스트 사이즈
			criteria.setPage(page); // page 설정
			criteria.setSearchText(searchText); // 검색 값 설정
			criteria.setSearchType(searchType); // 검색 유형 설정
			pageMaker.setTotalCount(enrollmentsList.size()); // 페이지 개수를 전체 리스트 크기로 설정
			enrollmentsList = enrollmentService.listCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
			System.out.println("page@@@"+page+"번호에 해당하는 리스트 크기"+enrollmentsList.size());
			
			mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내기
			mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
		} else { // 검색 유형이랑 값을 받지 않았다면
			System.out.println("@@@@@@@@검색필터적용안됨요");
			enrollmentsList = enrollmentService.listEnrollments(); //전체 리스트 저장
//			membersList = memberService.listCriteria(criteria);
			System.out.println(enrollmentsList.size()); //전체 사이즈
			criteria.setPage(page); //페이지 설정
			pageMaker.setTotalCount(enrollmentsList.size()); //페이지 개수 설정
			enrollmentsList = enrollmentService.listCriteria(criteria); //기준에 의해 나눠진 리스트 설정
		}
		System.out.println("perPage@@@@@@@@@@@@"+perPage);
		mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
		mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
		mav.addObject("membersList", enrollmentsList); //설정된 리스트 보내기
		return mav; //리스트 페이지로
	}
	

	
	
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//	
//	//수강신청내역 리스트로 이동 (백업)
//	@Override
//	@RequestMapping(value="/enrollment/listEnrollments.do" ,method = RequestMethod.GET)
//	public ModelAndView listEnrollments(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = (String)request.getAttribute("viewName");
//		List enrollmentsList = enrollmentService.listEnrollments();
//		ModelAndView mav = new ModelAndView(viewName);
//		
//		mav.addObject("enrollmentsList", enrollmentsList);
//		return mav;
//	}

	//상세 접수내역 페이지로 이동
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
	
	//수강신청 등록 
	@Override
	@RequestMapping(value="/enrollment/addEnrollment" ,method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView addEnrollment(@ModelAttribute("enrollment") EnrollmentVO enrollmentVO,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = enrollmentService.addEnrollment(enrollmentVO);
		ModelAndView mav = new ModelAndView("redirect:/enrollment/listEnrollments.do");
		return mav;
	}
	
	
	//아이디 중복신청 체크
	@Override
	@RequestMapping(value="/enrollment/checkEnrollment.do", method = RequestMethod.POST)
	public int checkEnrollment(@ModelAttribute("enrollment") EnrollmentVO enrollmentVO, 
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		int result = enrollmentService.checkEnrollment(enrollmentVO);
		System.out.println("라멜라@@@@@@ :   " + result);
		return result;
	}
	
	// 상세페이지 상태 수정
	@Override
	@RequestMapping(value="/enrollment/modEnrollment.do" ,method = RequestMethod.POST)
	public ModelAndView modEnrollment(@ModelAttribute("enrollment") EnrollmentVO enrollment,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = enrollmentService.modEnrollment(enrollment);
		ModelAndView mav = new ModelAndView("redirect:/enrollment/listEnrollments.do");
	//	ModelAndView mav = new ModelAndView("redirect:/enrollment/informationEnrollment.do?id=60000");
		return mav;
	}
	
	
	// 여러개 상태 수정
	@Override
	@ResponseBody
	@RequestMapping(value="/enrollment/modEnrollments.do", method = RequestMethod.POST)
	public int updateEnrollments(String [] arr, 
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		for(int i = 0; i < arr.length; i++) { 
			result = enrollmentService.updateEnrollments(Integer.parseInt(arr[i]));
		 } 
		return result;
	}

	//수강 등록 - 강의 리스트 출력
	@Override
	@RequestMapping(value = "/enrollment/enrollmentForm.do", method = RequestMethod.GET)
	public ModelAndView enrollmentForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		List syllabusesCoursesList = enrollmentService.listSylCrs();
		mav.addObject("syllabusesCoursesList", syllabusesCoursesList);
		return mav;
	}
	
	
//	//폼
//	@RequestMapping(value = "/enrollment/enrollmentForm.do", method = RequestMethod.GET)
//	private ModelAndView enrollmentForm(@RequestParam(value = "result", required = false) String result,
//			@RequestParam(value = "action", required = false) String action, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		String viewName = (String) request.getAttribute("viewName");
//		HttpSession session = request.getSession();
//		session.setAttribute("action", action);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("result", result);
//		mav.setViewName(viewName);
//		return mav;
//	}
	
}
