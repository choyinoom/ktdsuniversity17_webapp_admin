package com.ktdsuniversity.admin.satisfaction.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;
import com.ktdsuniversity.admin.satisfaction.vo.SatisfactionVO;


@Repository("SatisfactionDAO")
public class SatisfactionDAOImpl implements SatisfactionDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllSatisfactionsList() throws DataAccessException {
		List<SatisfactionVO> satisfactionsList = null;
		satisfactionsList = sqlSession.selectList("mapper.satisfaction.selectAllSatisfactionsList");
		return satisfactionsList;
	}
	
	@Override
	public List selectBySearchSatisfactionList(String searchType, String searchText) throws DataAccessException {
		List<SatisfactionVO> satisfactionsListsBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		satisfactionsListsBySearchList = sqlSession.selectList("mapper.satisfaction.selectBySearchSatisfactionList", mapSearch);
		return satisfactionsListsBySearchList;
	}

	@Override
	public int insertSatisfaction(SatisfactionVO satisfactionVO)  throws DataAccessException{
		int result = sqlSession.insert("mapper.satisfaction.insertSatisfaction", satisfactionVO);
		return result;
		
	}
	
	@Override
	public List<SatisfactionVO> listPaging(int page) throws DataAccessException {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlSession.selectList("mapper.satisfaction.selectSatisfactionListByPaging", page);
	}
	
	// 페이지 기준 설정 메서드
	@Override
	public List<SatisfactionVO> listCriteria(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.satisfaction.listCriteria", criteria);
	}
	
	//criteria에 의해 리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.satisfaction.selectCriteriaBySearchSatisfactionList", criteria);
	}
	
	@Override
	public int deleteSatisfaction(int id) throws DataAccessException {
		int result = sqlSession.delete("mapper.satisfaction.deleteSatisfaction", id);
		return result;
	}

	@Override
	public SatisfactionVO selectSatisfaction(int id) throws DataAccessException {
		SatisfactionVO vo = sqlSession.selectOne("mapper.satisfaction.selectSatisfaction", id);
		return vo;
	}

	@Override
	public int selectCheckSatisfaction(int id) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.satisfaction.selectCheckSatisfaction", id);
		return result;
	}
	
	@Override
	public int updateSatisfaction(SatisfactionVO satisfactionVO) throws DataAccessException {
		int result = sqlSession.update("mapper.satisfaction.updateSatisfaction", satisfactionVO);
		return result;
	}


	
}
