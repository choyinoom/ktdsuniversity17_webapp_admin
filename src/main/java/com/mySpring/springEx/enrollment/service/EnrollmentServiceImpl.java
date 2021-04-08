package com.mySpring.springEx.enrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.enrollment.dao.EnrollmentDAO;

@Service("enrollmentService")
@Transactional(propagation = Propagation.REQUIRED)
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	private EnrollmentDAO enrollmentDAO;

	@Override
	public List listEnrollments() throws DataAccessException {
		List enrollmentsList = null;
		enrollmentsList = enrollmentDAO.selectAllEnrollmentList();
		return enrollmentsList;
	}
	
	// ���� "����" ���� �ٲ���
	@Override 
	public int updateEnrollment(int id) throws DataAccessException {
		return enrollmentDAO.updateEnrollment(id);
	}
	
	// ���� "����" �� �ٲ���
	@Override 
	public int updateEnrollmentComplete(int id) throws DataAccessException {
		return enrollmentDAO.updateEnrollmentComplete(id); 
	}
	
	// ���� "���" �� �ٲ���
	@Override 
	public int updateEnrollmentCancel(int id) throws DataAccessException {
		return enrollmentDAO.updateEnrollmentCancel(id); 
	}
	
}
