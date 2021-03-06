package com.mySpring.springEx.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.company.dao.CompanyDAO;
import com.mySpring.springEx.company.vo.CompanyVO;

@Service("companyService")
@Transactional(propagation = Propagation.REQUIRED)
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyDAO companyDAO;
	
	@Override
	public List listCompanies() throws DataAccessException {
		List companiesList = null;
		companiesList = companyDAO.selectAllCompanyList();
		return companiesList;
	}

	@Override
	public List listBySearchCompanies(CompanyVO companyVO) throws DataAccessException {
		List companiesBySearchList = null;
		companiesBySearchList = companyDAO.selectBySearchMemberList(companyVO);
		return companiesBySearchList;
	}

	@Override
	public CompanyVO selectCompany(String comId) throws DataAccessException {
		return companyDAO.selectCompany(comId);
	}
}
