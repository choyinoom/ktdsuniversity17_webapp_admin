package com.ktdsuniversity.admin.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.member.vo.MemberVO;


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
		System.out.println("@@ page 번호 : " + criteria.getPage());
		System.out.println("@@ 페이지 스타트" + criteria.getPageStart());
		System.out.println("@@ 개당 페이지 개수" + criteria.getPerPageNum());
		return sqlSession.selectList("mapper.member.listCriteria", criteria);
	}
	
	//criteria에 의해 리스트 나누는 메서드
	@Override
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("pageStart" + criteria.getPageStart());
		System.out.println("perPageNum:" + criteria.getPerPageNum());
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

	@Override
	public int insertMemberLog(Map<String, String> map) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMemberLog", map);
		return result;
	}

	@Override
	public int insertLogByInformationInquiry(Map<String, String> map) throws DataAccessException {
		return sqlSession.insert("mapper.member.insertLogByInformationInquiry", map);
	}

	@Override
	public int updateMemberLog(Map<String, String> map) throws DataAccessException {
		return sqlSession.insert("mapper.member.updateMemberLog", map);
	}

	@Override
	public int addMemberLog(Map<String, String> map) throws DataAccessException {
		return sqlSession.insert("mapper.member.addMemberLog", map);
	}

	
}
