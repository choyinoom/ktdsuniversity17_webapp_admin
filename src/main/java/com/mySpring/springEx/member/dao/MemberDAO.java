package com.mySpring.springEx.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.member.vo.MemberVO;

public interface MemberDAO {
	public List selectAllMemberList() throws DataAccessException;
	public List selectBySearchMemberList(String searchType, String searchText) throws DataAccessException;
	public List<MemberVO> listPaging(int page) throws DataAccessException;
	public List<MemberVO> listCriteria(Criteria criteria) throws DataAccessException;
	public int insertMember(MemberVO memberVO) throws DataAccessException ;
	public int deleteMember(String id) throws DataAccessException;
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	public int updateMember(MemberVO memberVO) throws DataAccessException;
	public MemberVO selectMember(String id) throws DataAccessException;
	public int selectCheckMember(String id) throws DataAccessException;
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException;
	public List selectBySearchMemberListPopup(String searchType, String searchText) throws DataAccessException;
	public List selectCriteriaBySearchPopup(Criteria criteria) throws DataAccessException;
	
}
