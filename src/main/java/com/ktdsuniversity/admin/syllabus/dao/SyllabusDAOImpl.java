package com.ktdsuniversity.admin.syllabus.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.syllabus.vo.SyllabusVO;


@Repository("syllabusDAO")
public class SyllabusDAOImpl implements SyllabusDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllSyllabusList() throws DataAccessException {
		List<SyllabusVO> syllabusesList = null;
		syllabusesList = sqlSession.selectList("mapper.syllabus.selectAllSyllabusList");
		return syllabusesList;
	}
	
	@Override
	public List selectBySearchSyllabusesList(String searchType, String searchText) {
		List<SyllabusVO> syllabusesBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		syllabusesBySearchList = sqlSession.selectList("mapper.syllabus.selectBySearchSyllabusesList", mapSearch);
		return syllabusesBySearchList;
	}
	
	@Override
	public List<SyllabusVO> listPaging(int page) throws DataAccessException {
		if (page <= 0) {
			page = 1;
		} 
		
		page = (page - 1) * 10;
		return sqlSession.selectList("mapper.syllabus.selectSyllabusesListByPaging", page);
	}
	
	@Override
	public List<SyllabusVO> listCriteria(Criteria criteria) {
		return sqlSession.selectList("mapper.syllabus.listCriteria", criteria);
	}

	@Override
	public List selectCriteriaBySearch(Criteria criteria) {
		return sqlSession.selectList("mapper.syllabus.selectCriteriaBySearchSyllabusesList", criteria);
	}

	@Override
	public int insertSyllabus(SyllabusVO syllabusVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.syllabus.insertSyllabus", syllabusVO);
		return result;
	}

	@Override
	public int deleteSyllabus(int id) throws DataAccessException {
		int result = sqlSession.delete("mapper.syllabus.deleteSyllabus", id);
		return result;
	}

	@Override
	public SyllabusVO selectSyllabus(int id) throws DataAccessException {
		SyllabusVO syllabusVO = sqlSession.selectOne("mapper.syllabus.selectSyllabus", id);
		return syllabusVO;
	}

	@Override
	public int updateSyllabus(SyllabusVO syllabusVO) throws DataAccessException {
		int result = sqlSession.update("mapper.syllabus.updateSyllabus", syllabusVO);
		return result;
	}

	@Override
	public int selectMaxSyllabusId() throws DataAccessException {
		return sqlSession.selectOne("mapper.syllabus.selectMaxSyllabusId");
	}

}
