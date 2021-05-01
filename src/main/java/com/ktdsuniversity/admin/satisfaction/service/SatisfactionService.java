package com.ktdsuniversity.admin.satisfaction.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.satisfaction.vo.SatisfactionVO;





public interface SatisfactionService {

	public List listSatisfactions() throws DataAccessException;

	public List listCriteria(Criteria criteria) throws DataAccessException;

	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;

	public List listBySearchSatisfactions(String searchType, String searchText) throws DataAccessException;

	public int addSatisfaction(SatisfactionVO satisfactionVO) throws DataAccessException;

	public int idCheckSatisfaction(int id) throws DataAccessException;

	public int removeSatisfaction(int id) throws DataAccessException;

	public SatisfactionVO selectSatisfaction(int id) throws DataAccessException;

	public int updateSatisfaction(SatisfactionVO satisfactionVO) throws DataAccessException;


}
