package com.mySpring.springEx.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.springEx.manager.service.ManagerService;
import com.mySpring.springEx.manager.vo.ManagerVO;
import com.mySpring.springEx.member.vo.MemberVO;

@Controller("managerController")
public class ManagerControllerImpl implements ManagerController{

	@Autowired
	private ManagerService managerService;
	@Autowired
	ManagerVO managerVO;
	
	
	 @RequestMapping (value = {"/"}, method = RequestMethod.GET) 
	 private String main(HttpServletRequest request, HttpServletResponse response) {
		 return "";
	 }
	
	
	@Override
	@RequestMapping(value = "/manager/login.do", method = RequestMethod.POST) //loginForm.jsp의 정보를 토대로 login.do로 실행
	public ModelAndView login(@ModelAttribute("manager") ManagerVO manager,
				              RedirectAttributes rAttr, 
		                       HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView mav = new ModelAndView();
	managerVO = managerService.login(manager); //Serviceimpl의 매니저의 로그인 객체를 불러옴
	if(managerVO != null) {
	    HttpSession session = request.getSession();		//
	    session.setAttribute("manager", managerVO);		// 매니저의 정보를 Set시켜 정보표시
	    session.setAttribute("isLogOn", true);			//
	    //mav.setViewName("redirect:/member/listMembers.do");
	    String action = (String)session.getAttribute("action");
	    System.out.println(action);
//	    session.removeAttribute("action");
	    if(action!= null) {
	       mav.setViewName("redirect:"+action);
	    }else {
	       mav.setViewName("redirect:/main.do");	
	    }

	}else {
	   rAttr.addAttribute("result","loginFailed");	//로그인이 실패하였을경우 다시 로그인창으로 이동
	   mav.setViewName("redirect:/");
	}
	return mav;
	}

	@Override
	@RequestMapping(value = "/manager/logout.do", method =  RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("manager");		//로그아웃시 세션을 지워 매니저의 정보를 없애는 기능
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");			//로그인 창으로 이동
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/manager/modManager.do", method = RequestMethod.POST)
	public ModelAndView updateManager(@ModelAttribute("manager") ManagerVO manager,
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = managerService.updateManager(manager);
		HttpSession session = request.getSession();		//회원 정보를 수정하였을경우 세션을 지워 로그인창에서 다시 로그인하게되는기능
		session.removeAttribute("manager");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView("redirect:/");

//		int result1 = 1;
//		result1 = managerService.updateManager(manager);
	
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/manager/modManagerForm.do", method = RequestMethod.GET)
	public ModelAndView updateManagerForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String)request.getAttribute("viewName");
		System.out.println(viewName);
		HttpSession session = request.getSession();
		ManagerVO managerVO = (ManagerVO)session.getAttribute("manager"); //현재 로그인된 매니저의 정보를 가져옴

		ModelAndView mv = new ModelAndView();	
		mv.setViewName(viewName);
//		ManagerVO vo = managerService.selectManager(id);
		mv.addObject("managerVO", managerVO);
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
