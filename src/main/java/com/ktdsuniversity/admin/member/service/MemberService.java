package com.ktdsuniversity.admin.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.member.vo.MemberVO;

public interface MemberService {
	
	public List listMembers() throws DataAccessException;
	public List listBySearchMembers(String searchType, String searchText) throws DataAccessException;
	public List listCriteria(Criteria criteria) throws DataAccessException;
	public int addMember(MemberVO memberVO) throws DataAccessException;
	public int deleteMember(String id) throws DataAccessException;
	public MemberVO login(MemberVO memberVO) throws DataAccessException;
	public int updateMember(MemberVO memberVO) throws DataAccessException;
	public MemberVO selectMember(String id) throws DataAccessException;
	public int idCheckMember(String id) throws DataAccessException;
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;
	public List listBySearchMembersPopup(String searchType, String searchText) throws DataAccessException;
	public List listCriteriaBySearchPopup(Criteria criteria) throws DataAccessException;	
	public int removeMemberLog(Map<String, String> map) throws DataAccessException;
	public int updateMemberLog(Map<String, String> map) throws DataAccessException;
	public int addMemberLog(Map<String, String> map) throws DataAccessException;
	public int insertLogByInformationInquiry(Map<String, String> map) throws DataAccessException;
	
	
	
}
