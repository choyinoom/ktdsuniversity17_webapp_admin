package com.ktdsuniversity.admin.survey.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.survey.vo.SurveyVO;




public interface SurveyService {

	public List listSurveys() throws DataAccessException;
	public List listBySearchSurveys(String searchType, String searchText) throws DataAccessException;
	public int addSurvey(SurveyVO surveyVO) throws DataAccessException;
	public int removeSurvey(int id) throws DataAccessException;
	public SurveyVO selectSurvey(int id) throws DataAccessException;
	public int updateSurvey(SurveyVO surveyVO) throws DataAccessException;

	
	public List listCriteria(Criteria criteria) throws DataAccessException;
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;
	public int idCheckSurvey(int id) throws DataAccessException;
	
	

}
