package com.mySpring.springEx.enrollment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.enrollment.vo.EnrollmentVO;
import com.mySpring.springEx.member.vo.MemberVO;


public interface EnrollmentDAO {

	public List selectAllEnrollmentList() throws DataAccessException;

	public EnrollmentVO selectEnrollment(int id) throws DataAccessException;

	public int insertEnrollment(EnrollmentVO enrollmentVO) throws DataAccessException;

	public List selectSylCrsList() throws DataAccessException;

	public int modEnrollmentCompany(EnrollmentVO enrollmentVO) throws DataAccessException;

	public int modEnrollmentStat(EnrollmentVO enrollmentVO) throws DataAccessException;
	
	public int checkEnrollment(EnrollmentVO enrollment);

	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException;

	public List<EnrollmentVO> listCriteria(Criteria criteria) throws DataAccessException;

	public List selectBySearchEnrollmentList(String searchType, String searchText) throws DataAccessException;

	public List<EnrollmentVO> listPaging(int page) throws DataAccessException;

	public int updateDeleteEnrollments(int id) throws DataAccessException;
	
	public int updateApproveEnrollments(int id) throws DataAccessException;

	public int updateCompleteEnrollments(int id) throws DataAccessException;

	public List selectExceptList(String id) throws DataAccessException;

	public EnrollmentVO selectCompletion(int id) throws DataAccessException;

	public List selectBySearchCompletion(String searchType, String searchText) throws DataAccessException;

	public List<MemberVO> listPaging(int page) throws DataAccessException;

	public List<EnrollmentVO> listCriteria(Criteria criteria) throws DataAccessException;

	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException;

}
