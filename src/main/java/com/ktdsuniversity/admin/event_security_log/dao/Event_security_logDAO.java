package com.ktdsuniversity.admin.event_security_log.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.event_security_log.vo.Event_security_logVO;
import com.ktdsuniversity.admin.survey.vo.SurveyVO;




public interface Event_security_logDAO {
	
	

	public List selectAllLogsList() throws DataAccessException;
	
	public List<Event_security_logVO> listCriteria(Criteria criteria) throws DataAccessException;
	
	public List selectCriteriaBySearchLogList(Criteria criteria) throws DataAccessException;

	public List selectBySearchLogList(String searchType, String searchText) throws DataAccessException;
	
	public List<Event_security_logVO> listPaging(int page) throws DataAccessException;

	


}
