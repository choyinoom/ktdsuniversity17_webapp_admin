package com.ktdsuniversity.admin.event_security_log.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.event_security_log.dao.Event_security_logDAO;
import com.ktdsuniversity.admin.event_security_log.vo.Event_security_logVO;
import com.ktdsuniversity.admin.survey.dao.SurveyDAO;
import com.ktdsuniversity.admin.survey.vo.SurveyVO;



@Service("event_security_logService")
@Transactional(propagation = Propagation.REQUIRED)
public class Event_security_logServiceImpl implements Event_security_logService {
	
	@Autowired
	private Event_security_logDAO event_security_logDAO;

	
	@Override
	
	public List listEvent_security_logs() throws DataAccessException {
		List event_security_logsList = null;
		event_security_logsList = event_security_logDAO.selectAllLogsList();
		return event_security_logsList;
	}
	
//	//기준 나누는 메서드 

	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return event_security_logDAO.listCriteria(criteria);
	}
	
	
	
	//검색과 기준에 의해 리스트 나눠지는 메서드
	
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List Event_security_logsCriteriaBySearch = null;
		Event_security_logsCriteriaBySearch = event_security_logDAO.selectCriteriaBySearchLogList(criteria);
		return Event_security_logsCriteriaBySearch;
	}	
		
	@Override
	public List listBySearchEvent_security_logs(String searchType, String searchText) throws DataAccessException {
		List event_security_logsBySearchList = null;
		event_security_logsBySearchList = event_security_logDAO.selectBySearchLogList(searchType, searchText);
		return event_security_logsBySearchList;
	}

	


}
