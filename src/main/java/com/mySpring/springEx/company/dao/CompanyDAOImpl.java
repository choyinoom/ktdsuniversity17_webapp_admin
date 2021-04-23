package com.mySpring.springEx.company.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.company.vo.CompanyVO;

@Repository("companyDAO")
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	SqlSession sqlSession;

	// 전체 회사 출력 메소드
	@Override
	public List selectAllCompanyList() throws DataAccessException {
		List<CompanyVO> companiesList = null;
		companiesList = sqlSession.selectList("mapper.company.selectAllCompaniesList");
		return companiesList;
	}

	// 회사 검색 메소드
	@Override
	public List selectBySearchCompanyList(String searchType, String searchText) throws DataAccessException {
		List<CompanyVO> companiesBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		companiesBySearchList = sqlSession.selectList("mapper.company.selectBySearchCompaniesList", mapSearch);
		return companiesBySearchList;
	}

	// 페이지 기준 설정 메서드
	@Override
	public List<CompanyVO> listCriteria(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.company.listCriteria", criteria);
	}

	// criteria에 의해 리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.company.selectCriteriaBySearchCompanyList", criteria);
	}

	// 전체 회사 출력 메소드
	@Override
	public List selectAllPartnersList() throws DataAccessException {
		List<CompanyVO> partnersList = null;
		partnersList = sqlSession.selectList("mapper.company.selectAllPartnersList");
		return partnersList;
	}

	// 협력회사 검색 메소드
	@Override
	public List selectBySearchPartnerList(String searchType, String searchText) throws DataAccessException {
		List<CompanyVO> partnersBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		partnersBySearchList = sqlSession.selectList("mapper.company.selectBySearchPartnersList", mapSearch);
		return partnersBySearchList;
	}

	// 협력회사 페이지 기준 설정 메서드
	@Override
	public List<CompanyVO> partnerListCriteria(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.company.partnerListCriteria", criteria);
	}

	// criteria에 의해 협력회리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearchToPartner(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.company.selectCriteriaBySearchPartnerList", criteria);
	}

	// 회사 등록 메소드
	@Override
	public int insertCompany(CompanyVO company) throws DataAccessException {
		int result = sqlSession.insert("mapper.company.insertCompany", company);
		return result;
	}

	// 회사 선택후 상세창 나오는 메소드
	@Override
	public CompanyVO selectCompany(String id) throws DataAccessException {
		CompanyVO vo = sqlSession.selectOne("mapper.company.selectCompany", id);
		return vo;
	}

	// 회사 수정 메소드
	@Override
	public int modCompany(CompanyVO company) throws DataAccessException {
		int result = sqlSession.update("mapper.company.updateCompany", company);
		return result;
	}

	// 회사 삭제 메소드
	@Override
	public int deleteCompany(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.company.deleteCompany", id);
		return result;
	}

	// 학생관리의 회사수정 팝업에서 회사 검색 메소드
	@Override
	public List selectBySearchFromPopUp(String searchType, String searchText) throws DataAccessException {
		List<CompanyVO> companiesBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		companiesBySearchList = sqlSession.selectList("mapper.company.selectBySearchFromPopUp", mapSearch);
		return companiesBySearchList;
	}

	// 학생관리의 회사수정 팝업에서 criteria에 의해 리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearchFromPopUp(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.company.selectCriteriaBySearchCompanyListFromPopUp", criteria);
	}

	// 페이징 리스트 뽑아오기 메소드
	@Override
	public List<CompanyVO> listPaging(int page) throws DataAccessException {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlSession.selectList("mapper.company.selectCompanyListByPaging", page);
	}

	// 페이징 협력회사 리스트 뽑아오기 메소드
	@Override
	public List<CompanyVO> partnerListPaging(int page) throws DataAccessException {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlSession.selectList("mapper.company.selectPartnerListByPaging", page);
	}
}
