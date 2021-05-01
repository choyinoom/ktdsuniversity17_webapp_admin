package com.ktdsuniversity.admin.company.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.company.vo.CompanyVO;

public interface CompanyService {
	
	// 전체 회사 출력 메소드
	public List listCompanies() throws DataAccessException;
	
	// 회사 검색 메소드
	public List listBySearchCompanies(String searchType, String searchText) throws DataAccessException;
	
	// 보여질 회사 개수 기준에 의해 나눠진 리스트
	public List listCriteria(Criteria criteria) throws DataAccessException;
		
	// 보여질 회사 개수 기준 설정에 의해 새로 받는 리스트 설정
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;

	// 협력회사 리스트 출력 메소드
	public List listPartners() throws DataAccessException;
	
	// 협력회사 검색 메소드
	public List listBySearchPartners(String searchType, String searchText) throws DataAccessException;
	
	// 보여질 협력회사 개수 기준에 의해 나눠진 협력회사 리스트
	public List partnerListCriteria(Criteria criteria) throws DataAccessException;
	
	// 보여질 협력회사 개수 기준 설정에 의해 새로 받는 협력회사 리스트 설정
	public List partnerListCriteriaBySearch(Criteria criteria) throws DataAccessException;
	
	// 회사 추가 메소드
	public int addCompany(CompanyVO companyVO) throws DataAccessException;

	// 회사 선택 메소드
	public CompanyVO selectCompany(String id) throws DataAccessException;
	
	// 회사 수정 메소드
	public int modCompany(CompanyVO companyVO) throws DataAccessException;
	
	// 회사 삭제 메소드
	public int removeCompany(String id) throws DataAccessException;
	
	// 팝업에서 검색 메소드
	public List searchFromPopUp(String searchType, String searchText) throws DataAccessException;
	
	public int numberOfCompanies() throws DataAccessException;

	// 팝업에서 기준 설정에 의해 새로 받는 리스트 설정
	public List listCriteriaBySearchFromPopUp(Criteria criteria) throws DataAccessException;
	
	// 전체 회사 엑셀 다운로드
	public void companyExcelDownload(HttpServletResponse response) throws Exception;
	
	// 협력회사 엑셀 다운로드
	public void partnersExcelDownload(HttpServletResponse response) throws Exception;
	
	// 사업자 등록번호 중복체크
	public int idCheck(CompanyVO vo) throws DataAccessException;

	
		
}
