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
	
	//���� ������ �޼��� 
	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return enrollmentDAO.listCriteria(criteria);
	}
	
	//�˻��� ���� �������� �޼���
	@Override
	public List listBySearchEnrollments(String searchType, String searchText) throws DataAccessException {
		List enrollmentsBySearchList = null;
		enrollmentsBySearchList = enrollmentDAO.selectBySearchEnrollmentList(searchType, searchText);
		return enrollmentsBySearchList;
	}
	
	//�˻��� ���ؿ� ���� ����Ʈ �������� �޼���
	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List enrollmentsCriteriaBySearch = null;
		enrollmentsCriteriaBySearch = enrollmentDAO.selectCriteriaBySearch(criteria);
		return enrollmentsCriteriaBySearch;
	}
	
	//(��ϰ���) ������û �Ǿ��ִ��� üũ
	@Override
	public int checkEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		return enrollmentDAO.checkEnrollment(enrollment);
	}

	
	//��� ������ ����Ʈ ���
	@Override
	public List listSylCrs() throws DataAccessException {
		List sylCrsList = null;
		sylCrsList = enrollmentDAO.selectSylCrsList();
		return sylCrsList;
	}
	
	//��� ������ ����Ʈ ��� (������)
	@Override
	public List exceptListSylCrs(String id) throws DataAccessException {
		List sylCrsList = null;
		sylCrsList = enrollmentDAO.selectExceptList(id);
		return sylCrsList;
	}
	
	//������û���� ����Ʈ�� �̵�
	@Override
	public List listEnrollments() throws DataAccessException {
		List enrollmentsList = null;
		enrollmentsList = enrollmentDAO.selectAllEnrollmentList();
		return enrollmentsList;
	}
	
	//������û ���
	@Override
	public int addEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		return enrollmentDAO.insertEnrollment(enrollment);
	}
	
	//�� �������� �������� �̵�
	@Override
	public EnrollmentVO selectEnrollment(int id) throws DataAccessException {
		return enrollmentDAO.selectEnrollment(id);
	}
	
	// �������� ���� ����
	@Override
	public int modEnrollment(EnrollmentVO enrollment) throws DataAccessException {
		
		enrollmentDAO.modEnrollmentCompany(enrollment);
		return enrollmentDAO.modEnrollmentStat(enrollment);
	}
	
	// ���� '����' �� ����
	@Override 
	public int updateDeleteEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateDeleteEnrollments(id);
	}
	
	// ���� '����' ���� ����
	@Override 
	public int updateApproveEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateApproveEnrollments(id);
	}
		
	// ���� '����' �� ����
	@Override 
	public int updateCompleteEnrollments(int id) throws DataAccessException {
		return enrollmentDAO.updateCompleteEnrollments(id);
	}
	
}
