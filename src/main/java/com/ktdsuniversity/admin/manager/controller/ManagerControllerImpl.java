package com.ktdsuniversity.admin.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ktdsuniversity.admin.manager.service.ManagerService;
import com.ktdsuniversity.admin.manager.vo.ManagerVO;
import com.ktdsuniversity.admin.member.vo.MemberVO;

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
	
	
	/* 로그인 실패 시 loginForm.do로 리다이렉트*/
	@RequestMapping (value = "/manager/loginForm.do", method = RequestMethod.GET)
	private ModelAndView form(@RequestParam(value = "result", required = false) String result,
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

	
	@Override
	@RequestMapping(value = "/manager/login.do", method = RequestMethod.POST) 
	public ModelAndView login(@ModelAttribute("manager") ManagerVO manager,
				              RedirectAttributes rAttr, 
		                       HttpServletRequest request, HttpServletResponse response) throws Exception {
	ModelAndView mav = new ModelAndView();
	managerVO = managerService.login(manager); //Serviceimpl�� �Ŵ����� �α��� ��ü�� �ҷ���
	if(managerVO != null) {
		int loginSuccess = managerService.logLoginInfo(manager);
	    HttpSession session = request.getSession();
	    session.setAttribute("manager", managerVO);
	    session.setAttribute("isLogOn", true);
	    //mav.setViewName("redirect:/member/listMembers.do");
	    String action = (String)session.getAttribute("action");
	    System.out.println(action);

	    if(action!= null) {
	       mav.setViewName("redirect:"+action);
	    }else {
	       mav.setViewName("redirect:/member/adminMain.do");	
	    }

	}else {
		int loginFail = managerService.logLoginFailInfo(manager);
	   rAttr.addAttribute("result","loginFailed");
	   mav.setViewName("redirect:/manager/loginForm.do");
	}
	return mav;
	}

	@Override
	@RequestMapping(value = "/manager/logout.do", method =  RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ManagerVO managerVO = (ManagerVO)session.getAttribute("manager");
		int logout = managerService.logLogoutInfo(managerVO);
		session.removeAttribute("manager");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");			//�α��� â���� �̵�
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/manager/modManager.do", method = RequestMethod.POST)
	public ModelAndView updateManager(@ModelAttribute("manager") ManagerVO manager,
					  HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = managerService.updateManager(manager);
		HttpSession session = request.getSession();		//ȸ�� ������ �����Ͽ������ ������ ���� �α���â���� �ٽ� �α����ϰԵǴ±��
		session.removeAttribute("manager");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView("redirect:/");

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
		ManagerVO managerVO = (ManagerVO)session.getAttribute("manager"); 

		ModelAndView mv = new ModelAndView();	
		mv.setViewName(viewName);

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
