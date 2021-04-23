package com.mySpring.springEx.enrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.common.paging.Criteria;
import com.mySpring.springEx.course.vo.CourseVO;
import com.mySpring.springEx.enrollment.dao.EnrollmentDAO;
import com.mySpring.springEx.enrollment.vo.EnrollmentVO;

@Service("enrollmentService")
@Transactional(propagation = Propagation.REQUIRED)
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	private EnrollmentDAO enrollmentDAO;
	
	//기준 나누는 메서드 
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return enrollmentDAO.listCriteria(criteria);
	}
	
	//검색에 의해 나눠지는 메서드
	@Override
	public List listBySearchEnrollments(String searchType, String searchText) throws DataAccessException {
		List enrollmentsBySearchList = null;
		enrollmentsBySearchList = enrollmentDAO.selectBySearchEnrollmentList(searchType, searchText);
		return enrollmentsBySearchList;
	}
	
	//검색과 기준에 의해 리스트 나눠지는 메서드
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List enrollmentsCriteriaBySearch = null;
		enrollmentsCriteriaBySearch = enrollmentDAO.selectCriteriaBySearch(criteria);
		return enrollmentsCriteriaBySearch;
	}
	
	//(등록관련) 수강신청 되어있는지 체크
	@Override
	public int checkEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		return enrollmentDAO.checkEnrollment(enrollment);
	}

	
	//등록 페이지 리스트 출력
	@Override
	public List listSylCrs() throws DataAccessException {
		List sylCrsList = null;
		sylCrsList = enrollmentDAO.selectSylCrsList();
		return sylCrsList;
	}
	
	//등록 페이지 리스트 출력 (수정중)
	@Override
	public List exceptListSylCrs(String id) throws DataAccessException {
		List sylCrsList = null;
		sylCrsList = enrollmentDAO.selectExceptList(id);
		return sylCrsList;
	}
	
	//수강신청내역 리스트로 이동
	@Override
	public List listEnrollments() throws DataAccessException {
		List enrollmentsList = null;
		enrollmentsList = enrollmentDAO.selectAllEnrollmentList();
		return enrollmentsList;
	}
	
	//수강신청 등록
	@Override
	public int addEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		return enrollmentDAO.insertEnrollment(enrollment);
	}
	
	//상세 접수내역 페이지로 이동
	@Override
	public EnrollmentVO selectEnrollment(int id) throws DataAccessException {
		return enrollmentDAO.selectEnrollment(id);
	}
	
	@Override
	public EnrollmentVO selectCompletion(int id) throws DataAccessException {
		return enrollmentDAO.selectCompletion(id);
	}
	
	//기준 나누는 메서드 
		@Override
		public List listCriteria(Criteria criteria) throws DataAccessException {
			return enrollmentDAO.listCriteria(criteria);
		}
		
		//검색에 의해 나눠지는 메서드
		@Override
		public List listBySearchCompletion(String searchType, String searchText) throws DataAccessException {
			List membersBySearchList = null;
			membersBySearchList = enrollmentDAO.selectBySearchCompletion(searchType, searchText);
			return membersBySearchList;
		}
		
		//검색과 기준에 의해 리스트 나눠지는 메서드
		@Override
		public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
			List membersCriteriaBySearch = null;
			membersCriteriaBySearch = enrollmentDAO.selectCriteriaBySearch(criteria);
			return membersCriteriaBySearch;
		}
	
	// 상세페이지 상태 수정
	@Override
	public int modEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		
		enrollmentDAO.modEnrollmentCompany(enrollment);
		return enrollmentDAO.modEnrollmentStat(enrollment);
	}
	
	// 상태 '삭제' 로 수정
	@Override 
	public int updateDeleteEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateDeleteEnrollments(id);
	}
	
	// 상태 '승인' 으로 수정
	@Override 
	public int updateApproveEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateApproveEnrollments(id);
	}
		
	// 상태 '수료' 로 수정
	@Override 
	public int updateCompleteEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateCompleteEnrollments(id);
	}
	
}
