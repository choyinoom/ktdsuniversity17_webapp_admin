package com.ktdsuniversity.admin.event_security_log.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.event_security_log.vo.Event_security_logVO;
import com.ktdsuniversity.admin.member.vo.MemberVO;
import com.ktdsuniversity.admin.survey.vo.SurveyVO;

@Repository("Event_security_logDAO")
public class Event_security_logDAOImpl implements Event_security_logDAO{
	
	@Autowired
	private SqlSession sqlSession;

	
	@Override
	public List selectAllLogsList() throws DataAccessException {
		List<Event_security_logVO> event_security_logsList = null;
		event_security_logsList = sqlSession.selectList("mapper.event_security_log.selectAllLogsList");
		return event_security_logsList;
	}
	
	
	@Override
	public List selectBySearchLogList(String searchType, String searchText) throws DataAccessException {
		List<Event_security_logVO> event_security_logsBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		event_security_logsBySearchList = sqlSession.selectList("mapper.event_security_log.selectBySearchLogList", mapSearch);
		return event_security_logsBySearchList;
	}
	
	@Override
	public List<Event_security_logVO> listPaging(int page) throws DataAccessException {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlSession.selectList("mapper.event_security_log.selectLogListByPaging", page);
	}
	
	// 페이지 기준 설정 메서드
	@Override
	public List<Event_security_logVO> listCriteria(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.event_security_log.listCriteria", criteria);
	}
	
	//criteria에 의해 리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearchLogList(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.event_security_log.selectCriteriaBySearchLogList", criteria);
	}
	
	
	
	
}
