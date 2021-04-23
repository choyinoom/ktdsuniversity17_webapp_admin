package com.mySpring.springEx.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.member.dao.MemberDAO;
import com.mySpring.springEx.member.vo.MemberVO;


@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	
	//기준 나누는 메서드 
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return memberDAO.listCriteria(criteria);
	}
	
	//검색에 의해 나눠지는 메서드
	@Override
	public List listBySearchMembers(String searchType, String searchText) throws DataAccessException {
		List membersBySearchList = null;
		membersBySearchList = memberDAO.selectBySearchMemberList(searchType, searchText);
		return membersBySearchList;
	}
	
	//팝업
	@Override
	public List listBySearchMembersPopup(String searchType, String searchText) throws DataAccessException {
		List membersBySearchList = null;
		membersBySearchList = memberDAO.selectBySearchMemberListPopup(searchType, searchText);
		return membersBySearchList;
	}
	
	//검색과 기준에 의해 리스트 나눠지는 메서드
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List membersCriteriaBySearch = null;
		membersCriteriaBySearch = memberDAO.selectCriteriaBySearch(criteria);
		return membersCriteriaBySearch;
	}
	
	//팝업
	@Override
	public List listCriteriaBySearchPopup(Criteria criteria) throws DataAccessException {
		List membersCriteriaBySearch = null;
		membersCriteriaBySearch = memberDAO.selectCriteriaBySearchPopup(criteria);
		return membersCriteriaBySearch;
	}

	@Override
	public int addMember(MemberVO member) throws DataAccessException {
		return memberDAO.insertMember(member);
	}

	@Override
	public int idCheckMember(String id) throws DataAccessException {
		return memberDAO.selectCheckMember(id);
	}

	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}
	
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException{
		return memberDAO.loginById(memberVO);
	}

	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.updateMember(memberVO);
	}

	@Override
	public MemberVO selectMember(String id) throws DataAccessException {
		return memberDAO.selectMember(id);
	}

	@Override
	public List enrollmentsList(Criteria criteria) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


	
}
