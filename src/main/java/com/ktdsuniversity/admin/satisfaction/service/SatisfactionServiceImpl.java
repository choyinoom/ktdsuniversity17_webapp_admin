package com.ktdsuniversity.admin.satisfaction.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.satisfaction.dao.SatisfactionDAO;
import com.ktdsuniversity.admin.satisfaction.vo.SatisfactionVO;



@Service("satisfactionService")
@Transactional(propagation = Propagation.REQUIRED)
public class SatisfactionServiceImpl implements SatisfactionService {
	
	@Autowired
	private SatisfactionDAO satisfactionDAO;

	
	@Override
	public List listSatisfactions() throws DataAccessException {
		List satisfactionsList = null;
		satisfactionsList = satisfactionDAO.selectAllSatisfactionsList();
		return satisfactionsList;
	}
	
//	//기준 나누는 메서드 
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return satisfactionDAO.listCriteria(criteria);
	}
	
	//검색과 기준에 의해 리스트 나눠지는 메서드
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List satisfactionsCriteriaBySearch = null;
		satisfactionsCriteriaBySearch = satisfactionDAO.selectCriteriaBySearch(criteria);
		return satisfactionsCriteriaBySearch;
	}	
		
	@Override
	public List listBySearchSatisfactions(String searchType, String searchText) throws DataAccessException {
		List satisfactionsBySearchList = null;
		satisfactionsBySearchList = satisfactionDAO.selectBySearchSatisfactionList(searchType, searchText);
		return satisfactionsBySearchList;
	}

	@Override
	public int addSatisfaction(SatisfactionVO satisfactionVO) throws DataAccessException {
		return satisfactionDAO.insertSatisfaction(satisfactionVO);
	}

	@Override
	public int idCheckSatisfaction(int id) throws DataAccessException {
		return satisfactionDAO.selectCheckSatisfaction(id);
	}
	
	@Override
	public int removeSatisfaction(int id) throws DataAccessException {
		return satisfactionDAO.deleteSatisfaction(id);
	}

	@Override
	public SatisfactionVO selectSatisfaction(int id) throws DataAccessException {
		return satisfactionDAO.selectSatisfaction(id);
	}

	@Override
	public int updateSatisfaction(SatisfactionVO satisfactionVO) throws DataAccessException {
		return satisfactionDAO.updateSatisfaction(satisfactionVO);
	}
	
	

}
