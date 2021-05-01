package com.ktdsuniversity.admin.enrollment.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.enrollment.vo.EnrollmentVO;
import com.ktdsuniversity.admin.member.vo.MemberVO;


public interface EnrollmentService {

	public List listEnrollments() throws DataAccessException;
	public List listCompletion() throws DataAccessException;
	public int numberOfApplicants() throws DataAccessException;

	public EnrollmentVO selectEnrollment(int id) throws DataAccessException;

	public int modEnrollment(EnrollmentVO enrollment) throws DataAccessException;

	public int addEnrollment(EnrollmentVO enrollment) throws DataAccessException;

	public List listSylCrs() throws DataAccessException;

	//public int checkEnrollment(EnrollmentVO enrollment) throws DataAccessException;

	public List listEnrollmentCriteria(Criteria criteria) throws DataAccessException;

	public List listBySearchEnrollments(String searchType, String searchText) throws DataAccessException;

	public List listEnrollmentCriteriaBySearch(Criteria criteria) throws DataAccessException;
	
	public int updateDeleteEnrollments(int id) throws DataAccessException;

	public int updateApproveEnrollments(int id) throws DataAccessException;

	public int updateCompleteEnrollments(int id) throws DataAccessException;

	public void excelDownload(HttpServletResponse response) throws Exception;

	public List enrollmentCourse(int id) throws DataAccessException;

	public EnrollmentVO selectCompletion(int id) throws DataAccessException;

	public List listCompletionCriteria(Criteria criteria) throws DataAccessException;

	public List listBySearchCompletion(String searchType, String searchText) throws DataAccessException;

	public List listCompletionCriteriaBySearch(Criteria criteria) throws DataAccessException;
	
	public int numberOfWaitingCompletion() throws DataAccessException;
	
	public int logExcelDownload(String reason) throws DataAccessException;

}