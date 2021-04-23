package com.mySpring.springEx.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.member.vo.MemberVO;


@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public List selectBySearchMemberList(String searchType, String searchText) throws DataAccessException {
		List<MemberVO> membersBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		membersBySearchList = sqlSession.selectList("mapper.member.selectBySearchMemberList", mapSearch);
		return membersBySearchList;
	}
	
	//팝업용
	@Override
	public List selectBySearchMemberListPopup(String searchType, String searchText) throws DataAccessException {
		List<MemberVO> membersBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		membersBySearchList = sqlSession.selectList("mapper.member.selectBySearchMemberListPopup", mapSearch);
		return membersBySearchList;
	}
	
	//페이징 리스트 뽑아오기 메서드
	@Override
	public List<MemberVO> listPaging(int page) throws DataAccessException {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlSession.selectList("mapper.member.selectMemberListByPaging", page);
	}
	
	// 페이지 기준 설정 메서드
	@Override
	public List<MemberVO> listCriteria(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.member.listCriteria", criteria);
	}
	
	//criteria에 의해 리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.member.selectCriteriaBySearchMemberList", criteria);
	}
	
	//팝업용
	@Override
	public List selectCriteriaBySearchPopup(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.member.selectCriteriaBySearchMemberListPopup", criteria);
	}
	
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
	
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		  MemberVO vo = sqlSession.selectOne("mapper.member.loginById",memberVO);
		return vo;
	}

	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.update("mapper.member.updateMember", memberVO);
		return result;
	}

	@Override
	public MemberVO selectMember(String id) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.selectMember", id);
		return vo;
	}

	@Override
	public int selectCheckMember(String id) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.member.selectCheckMember", id);
		return result;
	}

	
}
