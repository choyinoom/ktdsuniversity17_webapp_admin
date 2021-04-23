package com.mySpring.springEx.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.member.vo.MemberVO;

public interface MemberService {
	
	public List listMembers() throws DataAccessException;
	public List listBySearchMembers(String searchType, String searchText) throws DataAccessException;
	public List listCriteria(Criteria criteria) throws DataAccessException;
	public int addMember(MemberVO memberVO) throws DataAccessException;
	public int removeMember(String id) throws DataAccessException;
	public MemberVO login(MemberVO memberVO) throws DataAccessException;
	public int updateMember(MemberVO memberVO) throws DataAccessException;
	public MemberVO selectMember(String id) throws DataAccessException;
	public int idCheckMember(String id) throws DataAccessException;
	public List enrollmentsList(Criteria criteria) throws DataAccessException;
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;
	public List listBySearchMembersPopup(String searchType, String searchText) throws DataAccessException;
	public List listCriteriaBySearchPopup(Criteria criteria) throws DataAccessException;	
}
