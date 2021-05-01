package com.ktdsuniversity.admin.syllabus.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.syllabus.vo.SyllabusVO;

public interface SyllabusService {

	public List listSyllabuses() throws DataAccessException;
	public int addSyllabus(SyllabusVO syllabusVO) throws DataAccessException;
	public int removeSyllabus(int id)  throws DataAccessException;
	public SyllabusVO selectSyllabus(int id) throws DataAccessException;
	public int updateSyllabus(SyllabusVO syllabusVO) throws DataAccessException;
	public List listBySearchSyllabuses(String searchType, String searchText) throws DataAccessException;
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;
	public List listCriteria(Criteria criteria) throws DataAccessException;
	public int selectMaxSyllabusId() throws DataAccessException;

}
