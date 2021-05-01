package com.ktdsuniversity.admin.event_security_log.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;

import com.ktdsuniversity.admin.event_security_log.vo.Event_security_logVO;





public interface Event_security_logService {

	public List listEvent_security_logs() throws DataAccessException;

	public List listCriteria(Criteria criteria) throws DataAccessException;

	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;

	public List listBySearchEvent_security_logs(String searchType, String searchText) throws DataAccessException;

	


}
