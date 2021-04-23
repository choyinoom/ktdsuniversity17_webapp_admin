package com.mySpring.springEx.enrollment.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.enrollment.vo.EnrollmentVO;
import com.mySpring.springEx.member.vo.MemberVO;


public interface EnrollmentService {

	public List listEnrollments() throws DataAccessException;

	public List listCompletion() throws DataAccessException;

	public EnrollmentVO selectEnrollment(int id) throws DataAccessException;

	public int modEnrollment(EnrollmentVO enrollment) throws DataAccessException;

	public int addEnrollment(EnrollmentVO enrollment) throws DataAccessException;

	public List listSylCrs() throws DataAccessException;

	public int checkEnrollment(EnrollmentVO enrollment) throws DataAccessException;

	public List listEnrollmentCriteria(Criteria criteria) throws DataAccessException;

	public List listBySearchEnrollments(String searchType, String searchText) throws DataAccessException;

	public List listEnrollmentCriteriaBySearch(Criteria criteria) throws DataAccessException;
	
	public int updateDeleteEnrollments(int id) throws DataAccessException;

	public int updateApproveEnrollments(int id) throws DataAccessException;

	public int updateCompleteEnrollments(int id) throws DataAccessException;

	public List exceptListSylCrs(String id) throws DataAccessException;

	public EnrollmentVO selectCompletion(int id) throws DataAccessException;

	public List listCompletionCriteria(Criteria criteria) throws DataAccessException;

	public List listBySearchCompletion(String searchType, String searchText) throws DataAccessException;

	public List listCompletionCriteriaBySearch(Criteria criteria) throws DataAccessException;

}
