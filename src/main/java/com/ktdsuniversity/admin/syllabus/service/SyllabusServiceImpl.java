package com.ktdsuniversity.admin.syllabus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.syllabus.dao.SyllabusDAO;
import com.ktdsuniversity.admin.syllabus.vo.SyllabusVO;

@Service("syllabusService")
@Transactional(propagation = Propagation.REQUIRED)
public class SyllabusServiceImpl implements SyllabusService{
	
	@Autowired
	private SyllabusDAO syllabusDAO;
	
	@Override
	public List listSyllabuses() throws DataAccessException {
		List syllabusesList = null;
		syllabusesList = syllabusDAO.selectAllSyllabusList();
		return syllabusesList;
	}
	
	@Override
	public List listCriteria(Criteria criteria) {
		return syllabusDAO.listCriteria(criteria);
	}
	
	@Override
	public List listBySearchSyllabuses(String searchType, String searchText) {
		List syllabusesBySearchList = null;
		syllabusesBySearchList = syllabusDAO.selectBySearchSyllabusesList(searchType, searchText);
		return syllabusesBySearchList;
	}

	@Override
	public List listCriteriaBySearch(Criteria criteria) {
		List syllabusesCriteriaBySearch = null;
		syllabusesCriteriaBySearch = syllabusDAO.selectCriteriaBySearch(criteria);
		return syllabusesCriteriaBySearch;
	}

	@Override
	public int addSyllabus(SyllabusVO syllabusVO) throws DataAccessException {
		return syllabusDAO.insertSyllabus(syllabusVO);
	}

	@Override
	public int removeSyllabus(int id) throws DataAccessException {
		return syllabusDAO.deleteSyllabus(id);
	}

	@Override
	public SyllabusVO selectSyllabus(int id) throws DataAccessException {
		return syllabusDAO.selectSyllabus(id);
	}

	@Override
	public int updateSyllabus(SyllabusVO syllabusVO) throws DataAccessException {
		return syllabusDAO.updateSyllabus(syllabusVO);
	}

	@Override
	public int selectMaxSyllabusId() throws DataAccessException {
		return syllabusDAO.selectMaxSyllabusId();
	}

	

}
