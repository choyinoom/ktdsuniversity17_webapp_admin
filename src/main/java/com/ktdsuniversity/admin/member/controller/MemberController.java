package com.ktdsuniversity.admin.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ktdsuniversity.admin.member.vo.MemberVO;


public interface MemberController {
	ModelAndView adminMain(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	int idCheckMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView updateMember(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView updateMemberForm(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception; 
	ModelAndView login(@ModelAttribute("member") MemberVO member,
                              RedirectAttributes rAttr,
                              HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	int removeCheckedMembers(String [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	ModelAndView informationMemberForm(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	int insertLogByInformationInquiry(String id, @RequestParam String reason, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
}