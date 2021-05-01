package com.ktdsuniversity.admin.survey.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.survey.dao.SurveyDAO;
import com.ktdsuniversity.admin.survey.vo.SurveyVO;



@Service("surveyService")
@Transactional(propagation = Propagation.REQUIRED)
public class SurveyServiceImpl implements SurveyService {
	
	@Autowired
	private SurveyDAO surveyDAO;

	@Override
	public List listSurveys() throws DataAccessException {
		List surveysList = null;
		surveysList = surveyDAO.selectAllSurveysList();
		return surveysList;
	}
	
//	//기준 나누는 메서드 
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return surveyDAO.listCriteria(criteria);
	}
	
	//검색과 기준에 의해 리스트 나눠지는 메서드
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List surveysCriteriaBySearch = null;
		surveysCriteriaBySearch = surveyDAO.selectCriteriaBySearch(criteria);
		return surveysCriteriaBySearch;
	}	
		
	@Override
	public List listBySearchSurveys(String searchType, String searchText) throws DataAccessException {
		List surveysBySearchList = null;
		surveysBySearchList = surveyDAO.selectBySearchSurveyList(searchType, searchText);
		return surveysBySearchList;
	}

	
	@Override
	public int addSurvey(SurveyVO surveyVO) throws DataAccessException {
		return surveyDAO.insertSurvey(surveyVO);
	}


	@Override
	public int idCheckSurvey(int id) throws DataAccessException {
		return surveyDAO.selectCheckSurvey(id);
	}
	
	
	@Override
	public int removeSurvey(int id) throws DataAccessException {
		return surveyDAO.deleteSurvey(id);
	}

	@Override
	public SurveyVO selectSurvey(int id) throws DataAccessException {
		return surveyDAO.selectSurvey(id);
	}

	@Override
	public int updateSurvey(SurveyVO surveyVO) throws DataAccessException {
		return surveyDAO.updateSurvey(surveyVO);
	}
	
	

}
