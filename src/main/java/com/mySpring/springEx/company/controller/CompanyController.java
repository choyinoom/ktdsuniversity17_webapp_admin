package com.mySpring.springEx.company.controller;

import java.lang.reflect.Member;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mySpring.springEx.company.vo.CompanyVO;

public interface CompanyController {
	
	// 전체회사 출력
	ModelAndView listCompanies(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 검색된 회사 출력
	ModelAndView listBySearchCompanies(@RequestParam("searchType") String searchType, @RequestParam("searchText") String searchText, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 협력회사 출력
	ModelAndView listPartners(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 협력회사 검색리스트 출력
	ModelAndView listBySearchPartners(@RequestParam("searchType") String searchType, @RequestParam("searchText") String searchText, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 회사 등록할 수 있는 메소드
	ModelAndView addCompany(@ModelAttribute("company")CompanyVO companyVO, HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	// 회사명 클릭했을 때
	ModelAndView selectCompany(@RequestParam String comId, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 회사 수정할 수 있는 메소드
	ModelAndView modCompany(@ModelAttribute("company")CompanyVO companyVO, HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	// 회사관리 상세창에서 선택된 회사를 삭제할 수 있는 메소드
	ModelAndView removeCompany(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception;
		
	// 회사관리 리스트에 있는 삭제 버튼으로 회원을 체크해서 삭제할 수 있는 메소드
	int removeCheckedCompanies(String [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception;
		
}
