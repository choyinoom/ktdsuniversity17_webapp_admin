package com.mySpring.springEx.company.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.company.vo.CompanyVO;

public interface CompanyDAO {

	// 전체 회사 출력 메소드
	public List selectAllCompanyList() throws DataAccessException;

	// 회사 검색 메소드
	public List selectBySearchCompanyList(String searchType, String searchText) throws DataAccessException;

	// 보여질 회사 개수 기준 나누는 메소드
	public List<CompanyVO> listCriteria(Criteria criteria) throws DataAccessException;

	// 보여질 회사 개수 검색에 의해 나눠지는 메서드
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException;

	// 협력회사 리스트 출력 메소드
	public List selectAllPartnersList() throws DataAccessException;

	// 협력회사 검색 메소드
	public List selectBySearchPartnerList(String searchType, String searchText) throws DataAccessException;

	// 기준 나누는 메소드 (협력회사)
	public List<CompanyVO> partnerListCriteria(Criteria criteria) throws DataAccessException;

	// 검색에 의해 나눠지는 메소드 (협력회사)
	public List selectCriteriaBySearchToPartner(Criteria criteria) throws DataAccessException;

	// 회사 추가 메소드
	public int insertCompany(CompanyVO company) throws DataAccessException;

	// 회사명 선택 메소드
	public CompanyVO selectCompany(String id) throws DataAccessException;

	// 회사 수정 메소드
	public int modCompany(CompanyVO company) throws DataAccessException;

	// 회사 삭제 메소드
	public int deleteCompany(String id) throws DataAccessException;

	// 학생관리 회사수정 팝업에서의 검색 메소드
	public List selectBySearchFromPopUp(String searchType, String searchText) throws DataAccessException;

	// 학생관리 회사수정 팝업에서의 검색에 의해 나눠지는 메서드
	public List selectCriteriaBySearchFromPopUp(Criteria criteria) throws DataAccessException;
	
	
	public List<CompanyVO> listPaging(int page) throws DataAccessException;

	public List<CompanyVO> partnerListPaging(int page) throws DataAccessException;
}
