package com.ktdsuniversity.admin.survey.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.member.vo.MemberVO;
import com.ktdsuniversity.admin.survey.vo.SurveyVO;

@Repository("SurveyDAO")
public class SurveyDAOImpl implements SurveyDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllSurveysList() throws DataAccessException {
		List<SurveyVO> surveysList = null;
		surveysList = sqlSession.selectList("mapper.survey.selectAllSurveysList");
		return surveysList;
	}
	
	@Override
	public List selectBySearchSurveyList(String searchType, String searchText) throws DataAccessException {
		List<SurveyVO> surveysBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		surveysBySearchList = sqlSession.selectList("mapper.survey.selectBySearchSurveyList", mapSearch);
		return surveysBySearchList;
	}


	@Override
	public int insertSurvey(SurveyVO surveyVO)  throws DataAccessException{
		int result = sqlSession.insert("mapper.survey.insertSurvey", surveyVO);
		return result;
		
	}
	//-------------------------------------------------------------------------------
	
	
	@Override
	public List<SurveyVO> listPaging(int page) throws DataAccessException {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlSession.selectList("mapper.survey.selectSurveyListByPaging", page);
	}
	
	// 페이지 기준 설정 메서드
	@Override
	public List<SurveyVO> listCriteria(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.survey.listCriteria", criteria);
	}
	
	//criteria에 의해 리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.survey.selectCriteriaBySearchSurveyList", criteria);
	}
	
	
	
	
	
	//-------------------------------------------------------------------------------
	
	
	@Override
	public int deleteSurvey(int id) throws DataAccessException {
		int result = sqlSession.delete("mapper.survey.deleteSurvey", id);
		return result;
	}

	@Override
	public SurveyVO selectSurvey(int id) throws DataAccessException {
		SurveyVO vo = sqlSession.selectOne("mapper.survey.selectSurvey", id);
		return vo;
	}


	@Override
	public int selectCheckSurvey(int id) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.survey.selectCheckSurvey", id);
		return result;
	}
	
	
	
	@Override
	public int updateSurvey(SurveyVO surveyVO) throws DataAccessException {
		int result = sqlSession.update("mapper.survey.updateSurvey", surveyVO);
		return result;
	}


	
}
