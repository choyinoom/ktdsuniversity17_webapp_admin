package com.mySpring.springEx.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.company.dao.CompanyDAO;
import com.mySpring.springEx.company.vo.CompanyVO;
import com.mySpring.springEx.course.dao.CourseDAO;

import oracle.net.aso.n;

@Service("companyService")
@Transactional(propagation = Propagation.REQUIRED)
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO companyDAO;

	// 전체 회사 출력 메소드
	@Override
	public List listCompanies() throws DataAccessException {
		List companiesList = null;
		companiesList = companyDAO.selectAllCompanyList();
		return companiesList;
	}

	// 회사 검색 메소드
	@Override
	public List listBySearchCompanies(String searchType, String searchText) throws DataAccessException {
		List companiesBySearchList = null;
		companiesBySearchList = companyDAO.selectBySearchCompanyList(searchType, searchText);
		return companiesBySearchList;
	}

	// 보여질 회사 개수 기준 나누는 메소드
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return companyDAO.listCriteria(criteria);
	}

	// 보여질 회사 개수 검색에 의해 나눠지는 메서드
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List companiesCriteriaBySearch = null;
		companiesCriteriaBySearch = companyDAO.selectCriteriaBySearch(criteria);
		return companiesCriteriaBySearch;
	}

	// 협력회사 출력 메소드
	@Override
	public List listPartners() throws DataAccessException {
		List partnersList = null;
		partnersList = companyDAO.selectAllPartnersList();
		return partnersList;
	}

	// 협력회사 검색 메소드
	@Override
	public List listBySearchPartners(String searchType, String searchText) throws DataAccessException {
		List partnersBySearchList = null;
		partnersBySearchList = companyDAO.selectBySearchPartnerList(searchType, searchText);
		return partnersBySearchList;
	}

	// 기준 나누는 메소드 (협력회사)
	@Override
	public List partnerListCriteria(Criteria criteria) throws DataAccessException {
		return companyDAO.partnerListCriteria(criteria);
	}

	// 검색에 의해 나눠지는 메소드 (협력회사)
	@Override
	public List partnerListCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List partnersCrteriaBySearch = null;
		partnersCrteriaBySearch = companyDAO.selectCriteriaBySearchToPartner(criteria);
		return partnersCrteriaBySearch;
	}

	// 회사 추가 메소드
	@Override
	public int addCompany(CompanyVO company) throws DataAccessException {
		return companyDAO.insertCompany(company);
	}

	// 회사명 선택 메소드
	@Override
	public CompanyVO selectCompany(String id) throws DataAccessException {
		return companyDAO.selectCompany(id);
	}

	// 회사 수정 메소드
	@Override
	public int modCompany(CompanyVO company) throws DataAccessException {
		return companyDAO.modCompany(company);
	}

	// 회사 삭제 메소드
	@Override
	public int removeCompany(String id) throws DataAccessException {
		return companyDAO.deleteCompany(id);
	}

	// 팝업창에서 검색 메소드
	@Override
	public List searchFromPopUp(String searchType, String searchText) throws DataAccessException {
		List companiesBySearchList = null;
		companiesBySearchList = companyDAO.selectBySearchFromPopUp(searchType, searchText);
		return companiesBySearchList;
	}

	// 팝업창에서 검색에 의해 나눠지는 메서드
	@Override
	public List listCriteriaBySearchFromPopUp(Criteria criteria) throws DataAccessException {
		List companiesCriteriaBySearch = null;
		companiesCriteriaBySearch = companyDAO.selectCriteriaBySearchFromPopUp(criteria);
		return companiesCriteriaBySearch;
	}
}
