package com.ktdsuniversity.admin.company.controller;

import java.lang.reflect.Member;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.company.vo.CompanyVO;

public interface CompanyController {
	
	// 전체 회사 출력
	ModelAndView listCompanies(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 검색 회사 출력
	ModelAndView listBySearchCompanies(@RequestParam("searchType") String searchType, @RequestParam("searchText") String searchText, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 협력회사 출력
	ModelAndView listPartners(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 협력회사 검색 출력
	ModelAndView listBySearchPartners(@RequestParam("searchType") String searchType, @RequestParam("searchText") String searchText, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 회사 등록
	ModelAndView addCompany(@ModelAttribute("company")CompanyVO companyVO, @ModelAttribute("criteria") Criteria criteria, RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	// 회사 선택
	ModelAndView selectCompany(@RequestParam String comId, @RequestParam String type, @ModelAttribute("criteria") Criteria criteria, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 회사 수정
	ModelAndView modCompany(@ModelAttribute("company")CompanyVO companyVO, @RequestParam("type") String type, @ModelAttribute("criteria") Criteria criteria, RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	// 회사 삭제
	ModelAndView removeCompany(@RequestParam("id") String id, @ModelAttribute("criteria") Criteria criteria, RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
		
	// 체크한 회사 삭제
	int removeCheckedCompanies(String [] arr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 전체 회사 엑셀 다운로드
	void companyExcelDownload(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 협력회사 엑셀 다운로드
	void partnersExcelDownload(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
