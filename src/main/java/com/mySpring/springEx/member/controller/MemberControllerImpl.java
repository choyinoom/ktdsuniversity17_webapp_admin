package com.mySpring.springEx.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.common.paging.PageMaker;
import com.mySpring.springEx.member.service.MemberService;
import com.mySpring.springEx.member.vo.MemberVO;

@Controller("memberController")
//@EnableAspectJAutoProxy
public class MemberControllerImpl implements MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO;

	@RequestMapping(value = { "/main.do" }, method = RequestMethod.GET)
	private String main(HttpServletRequest request, HttpServletResponse response) {
//		String viewName = (String)request.getAttribute("viewName");
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName(viewName);
		return "main";
	}
	
	//아이디 검색 팝업 (원본)
//	@RequestMapping(value = "/member/memberSearchPopUp.do", method = {RequestMethod.GET, RequestMethod.POST})
//	private ModelAndView memberSearchPopUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = (String)request.getAttribute("viewName");
//		List membersList = memberService.listMembers();
//		ModelAndView mv = new ModelAndView(viewName);
//		mv.addObject("membersList", membersList);
//		return mv;
//	}
	
	//아이디 검색 팝업 (검색, 페이징 기능 작업중)
	@RequestMapping(value = "/member/memberSearchPopUp.do", method = {RequestMethod.GET, RequestMethod.POST})
	private ModelAndView memberSearchPopUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		String searchType = (String)request.getParameter("searchType");
		String searchText = (String)request.getParameter("searchText");
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt((String)request.getParameter("page")); //page 변수에 값을 저장
		} else {
			page = 1; //아니면 page 1로 기본 지정
		}
		
		int perPage = 10;
		
		List membersList = null; 
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();
		
		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 기준 값 설정
		//여기까지 진행 @@
		if (searchType != null && searchText != null) { // 검색 유형이랑 값을 받았다면
			membersList = memberService.listBySearchMembersPopup(searchType, searchText);
			criteria.setPage(page); // page 설정
			criteria.setSearchText(searchText); // 검색 값 설정
			criteria.setSearchType(searchType); // 검색 유형 설정
			pageMaker.setTotalCount(membersList.size()); // 페이지 개수를 전체 리스트 크기로 설정
			membersList = memberService.listCriteriaBySearchPopup(criteria); // 기준 설정에 의해 새로 받는 리스트
			
			mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내기
			mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
		} else { // 검색 유형이랑 값을 받지 않았다면
			membersList = memberService.listMembers(); //전체 리스트 저장
			criteria.setPage(page); //페이지 설정
			pageMaker.setTotalCount(membersList.size()); //페이지 개수 설정
			membersList = memberService.listCriteria(criteria); //기준에 의해 나눠진 리스트 설정
		}
		mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
		mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기		
		mav.addObject("membersList", membersList); //설정된 리스트 보내기
	
		
		return mav;
	}

	@Override
	@RequestMapping(value="/member/listMembers.do", method =  RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response
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

		List membersList = null; 
		ModelAndView mav = new ModelAndView(viewName);
		Criteria criteria = new Criteria();
		PageMaker pageMaker = new PageMaker();
		
		criteria.setPerPageNum(perPage); // 리스트 개수 설정
		pageMaker.setCriteria(criteria); // 기준 값 설정
		
		if (searchType != null && searchText != null) { // 검색 유형이랑 값을 받았다면
			System.out.println(searchType);
			System.out.println(searchText);
			System.out.println("@@@@@@@@@검색필터 적용됨요");
			membersList = memberService.listBySearchMembers(searchType, searchText);
			System.out.println("@@@@@@@@@@서치된 리스트"+membersList.size()); // 검색해서 받은 전체 리스트 사이즈
			criteria.setPage(page); // page 설정
			criteria.setSearchText(searchText); // 검색 값 설정
			criteria.setSearchType(searchType); // 검색 유형 설정
			pageMaker.setTotalCount(membersList.size()); // 페이지 개수를 전체 리스트 크기로 설정
			membersList = memberService.listCriteriaBySearch(criteria); // 기준 설정에 의해 새로 받는 리스트
			System.out.println("page@@@"+page+"번호에 해당하는 리스트 크기"+membersList.size());
			
			mav.addObject("searchText", searchText); // 검색 값 다시 페이지로 보내기
			mav.addObject("searchType", searchType); // 검색 유형 다시 페이지로 보내기
		} else { // 검색 유형이랑 값을 받지 않았다면
			System.out.println("@@@@@@@@검색필터적용안됨요");
			membersList = memberService.listMembers(); //전체 리스트 저장
//			membersList = memberService.listCriteria(criteria);
			System.out.println(membersList.size()); //전체 사이즈
			criteria.setPage(page); //페이지 설정
			pageMaker.setTotalCount(membersList.size()); //페이지 개수 설정
			membersList = memberService.listCriteria(criteria); //기준에 의해 나눠진 리스트 설정
		}
		System.out.println("perPage@@@@@@@@@@@@"+perPage);
		mav.addObject("perPage", perPage); // 리스트 기준 값 보내기
		mav.addObject("pageMaker", pageMaker); // 페이지 만들어진 값 보내기
		mav.addObject("membersList", membersList); //설정된 리스트 보내기
		return mav; //리스트 페이지로
	}
	
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
	

	@Override
	@RequestMapping(value="/member/addMember.do" ,method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/member/idCheckMember.do", method = RequestMethod.POST)
	public int idCheckMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		System.out.println(id);
		int result = memberService.idCheckMember(id);
		System.out.println(result);
		return result;
	}

	@Override
	@RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@ResponseBody
	@RequestMapping(value = "/member/removeCheckedMembers.do", method = RequestMethod.POST)
	public int removeCheckedMembers(String[] arr, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("============================" + arr.length);
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			result = memberService.removeMember(arr[i]);
		}
		return result;
	}

	@Override
	@RequestMapping(value = "/member/modMember.do", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.updateMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/modMemberForm.do", method = RequestMethod.GET)
	public ModelAndView updateMemberForm(@RequestParam String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		MemberVO vo = memberService.selectMember(id);
		mv.addObject("vo", vo);
		return mv;
	}

	@Override
	@RequestMapping(value = "/member/informationMemberForm.do", method = RequestMethod.GET)
	public ModelAndView informationMemberForm(@RequestParam String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		MemberVO vo = memberService.selectMember(id);	
		mv.addObject("vo", vo);
		return mv;
	}

	/*
	 * @RequestMapping(value = { "/member/loginForm.do", "/member/memberForm.do" },
	 * method = RequestMethod.GET)
	 * 
	 * @RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	 * public ModelAndView form(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception { String viewName = getViewName(request);
	 * ModelAndView mav = new ModelAndView(); mav.setViewName(viewName); return mav;
	 * }
	 */

	@Override
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			// mav.setViewName("redirect:/member/listMembers.do");
			String action = (String) session.getAttribute("action");
			session.removeAttribute("action");
			if (action != null) {
				mav.setViewName("redirect:" + action);
			} else {
				mav.setViewName("redirect:/main.do");
			}

		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}

	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
//		System.out.println(viewName);
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
