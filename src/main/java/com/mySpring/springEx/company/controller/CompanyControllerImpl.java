package com.mySpring.springEx.company.controller;

import java.text.Normalizer.Form;
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

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.common.paging.PageMaker;
import com.mySpring.springEx.company.service.CompanyService;
import com.mySpring.springEx.company.vo.CompanyVO;
import com.mySpring.springEx.course.vo.CourseVO;

@Controller("companyController")
public class CompanyControllerImpl implements CompanyController {

	@Autowired
	CompanyService companyService;

	// 전체회사 리스트 출력 메소드
	@Override
	@RequestMapping(value = "/company/listCompanies.do", method = RequestMethod.GET)
	public ModelAndView listCompanies(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		String searchType = (String) request.getParameter("searchType");
		String searchText = (String) request.getParameter("searchText");

		// 페이지 변수 선언
		int page = 0;
		
		// 페이지 값이 있으면
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); // 페이지 값 저장
		} else {
			page = 1; // 없으면 1 저장
		}

		int perPage = 0; // 리스트 개수 값 저장할 변수 생성
		
		// perPage 값 있으면
		if (request.getParameter("perPage") != null) {
			perPage = Integer.parseInt(request.getParameter("perPage")); // 페이지 값 저장
		} else {
			perPage = 10; // 없으면 10 저장
		}
		
		List companiesList = null;
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();

		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 기준 값 설정

		if (searchType != null && searchText != null) { // 검색 유형이랑 값을 받았으면
			companiesList = companyService.listBySearchCompanies(searchType, searchText);
			criteria.setPage(page); // 페이지 설정
			criteria.setSearchText(searchText); // 검색 값 설정
			criteria.setSearchType(searchType); // 검색 유형 설정
			pageMaker.setTotalCount(companiesList.size()); // 페이지 개수를 전체 리스트 크기로 설정
			companiesList = companyService.listCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
			mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내고
			mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
		} else { // type, text를 받지 않으면
			companiesList = companyService.listCompanies(); // 전체 리스트를 저장하고
			criteria.setPage(page); // 페이지 설정
			pageMaker.setTotalCount(companiesList.size()); // 페이지 개수 설정
			companiesList = companyService.listCriteria(criteria); // 기준에 의해 나눠진 리스트 설정
		}
		mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
		mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
		mav.addObject("companiesList", companiesList); // 설정된 리스트 보내기
		return mav;
	}

	// 회사관리에서 검색된 회사 출력
	@Override
	@ResponseBody
	@RequestMapping(value = "/company/listBySearch.do", method = RequestMethod.POST)
	public ModelAndView listBySearchCompanies(@RequestParam("searchType") String searchType,
			@RequestParam("searchText") String searchText, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List companiesList = companyService.listBySearchCompanies(searchType, searchText);
		ModelAndView mav = new ModelAndView("/company/listCompanies");
		mav.addObject("companiesList", companiesList);
		return mav;
	}

	// 협력회사 리스트 불러오는 메소드
	@Override
	@RequestMapping(value = "/company/listPartners.do", method = RequestMethod.GET)
	public ModelAndView listPartners(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");

		// 페이지 변수 선언
		int page = 0;
		// 페이지 값이 있으면
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); // 페이지 값 저장
		} else {
			page = 1; // 없으면 1 저장
		}

		int perPage = 0; // 리스트 개수 값 저장할 변수 생성
		
		// perPage 값 있으면
		if (request.getParameter("perPage") != null) {
			perPage = Integer.parseInt((String)request.getParameter("perPage")); // 페이지 값 저장
		} else {
			perPage = 10; // 없으면 10 저장
		}
		
		List partnersList = null;
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();

		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 기준 값 설정

		if (searchType != null && searchText != null) {
			partnersList = companyService.listBySearchPartners(searchType, searchText);
			criteria.setPage(page); // 페이지 설정
			criteria.setSearchText(searchText); // 검색 값 설정
			criteria.setSearchType(searchType); // 검색 유형 설정
			pageMaker.setTotalCount(partnersList.size()); // 페이지 개수를 전체 리스트 크기로 설정
			partnersList = companyService.partnerListCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
			
			mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내고
			mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
		} else { // 검색 유형이랑 값을 받지 않았다면
			partnersList = companyService.listPartners();
			criteria.setPage(page); // 페이지 설정
			pageMaker.setTotalCount(partnersList.size()); // 페이지 개수 설정
			partnersList = companyService.partnerListCriteria(criteria); // 기준에 의해 나눠진 리스트 설정
		}
		mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
		mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
		mav.addObject("partnersList", partnersList);
		return mav;
	}

	// 협력회사 검색 할 수 있는 메소드
	@Override
	@RequestMapping(value = "/company/listBySearchPartners.do", method = RequestMethod.POST)
	public ModelAndView listBySearchPartners(@RequestParam("searchType") String searchType,
			@RequestParam("searchText") String searchText, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List partnersList = companyService.listBySearchPartners(searchType, searchText);
		ModelAndView mav = new ModelAndView("/company/listPartners");
		mav.addObject("partnersList", partnersList);
		return mav;
	}

	// 회사 등록할 수 있는 메소드
	@Override
	@RequestMapping(value = "/company/addCompany.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addCompany(@ModelAttribute("company") CompanyVO companyVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = companyService.addCompany(companyVO);
		ModelAndView mav = new ModelAndView("redirect:/company/listCompanies.do");
		return mav;
	}

	// 협력회사, 회사관리 리스트의 회사명을 클릭하면 그 회사의 정보가 띄어지는 메소드
	@Override
	@RequestMapping(value = "/company/companyForm.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectCompany(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		CompanyVO companyVO = companyService.selectCompany(id);
		mav.addObject("companyVO", companyVO);
		return mav;
	}

	// 회사 수정할 수 있는 메소드
	@Override
	@RequestMapping(value = "/company/modCompany.do", method = RequestMethod.POST)
	public ModelAndView modCompany(@ModelAttribute("company") CompanyVO companyVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = companyService.modCompany(companyVO);
		ModelAndView mav = new ModelAndView("redirect:/company/listCompanies.do");
		return mav;
	}

	// 회사명을 선택하면 회사 상세창에서 회사를 삭제할 수 있는 메소드
	@Override
	@RequestMapping(value = "/company/removeCompany.do", method = RequestMethod.GET)
	public ModelAndView removeCompany(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		companyService.removeCompany(id);
		ModelAndView mav = new ModelAndView("redirect:/company/listCompanies.do");
		return mav;
	}

	// 회사 리스트 창에서 체크해서 회사를 삭제할 수 있는 메소드
	@Override
	@ResponseBody
	@RequestMapping(value = "/company/removeCheckedCompanies.do", method = RequestMethod.POST)
	public int removeCheckedCompanies(String[] arr, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			result = companyService.removeCompany(arr[i]);
		}
		return result;
	}

	// 학생관리의 수정창 안에 회사수정 팝업창
	@RequestMapping(value = "/company/popUp.do", method = { RequestMethod.GET, RequestMethod.POST })
	private ModelAndView popUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		String searchType = (String) request.getParameter("searchType");
		String searchText = (String) request.getParameter("searchText");

		// 페이지 변수 선언
		int page = 0;
		// 페이지 값이 있으면
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); // 페이지 값 저장
		} else {
			page = 1; // 없으면 1 저장
		}

		int perPage = 0; // 리스트 개수 값 저장할 변수 생성
		// perPage 값 있으면
		if (request.getParameter("perPage") != null) {
			perPage = Integer.parseInt(request.getParameter("perPage")); // 페이지 값 저장
		} else {
			perPage = 10; // 없으면 10 저장
		}
		List companiesList = companyService.listCompanies();
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();

		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 기준 값 설정

		if (searchType != null && searchText != null) { // 검색 유형이랑 값을 받았으면
			companiesList = companyService.searchFromPopUp(searchType, searchText);
			criteria.setPage(page); // 페이지 설정
			criteria.setSearchText(searchText); // 검색 값 설정
			criteria.setSearchType(searchType); // 검색 유형 설정
			pageMaker.setTotalCount(companiesList.size()); // 페이지 개수를 전체 리스트 크기로 설정
			companiesList = companyService.listCriteriaBySearchFromPopUp(criteria); // 기준 설정에 의해 새로 받는 리스트
			mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내고
			mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
		} else { // type, text를 받지 않으면
			companiesList = companyService.listCompanies(); // 전체 리스트를 저장하고
			criteria.setPage(page); // 페이지 설정
			pageMaker.setTotalCount(companiesList.size()); // 페이지 개수 설정
			companiesList = companyService.listCriteria(criteria); // 기준에 의해 나눠진 리스트 설정
		}
		mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
		mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
		mav.addObject("companiesList", companiesList); // 설정된 리스트 보내기
		return mav;
	}

	@RequestMapping(value = "/company/*Form.do", method = RequestMethod.GET)
	private ModelAndView Form(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
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
