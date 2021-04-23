package com.mySpring.springEx.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.manager.dao.ManagerDAO;
import com.mySpring.springEx.manager.vo.ManagerVO;

@Service("managerService")
@Transactional(propagation = Propagation.REQUIRED)
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private ManagerDAO managerDAO;
	
	@Override
	public ManagerVO login(ManagerVO managerVO) throws DataAccessException {
		return managerDAO.loginById(managerVO); //DAO에 loginById객체를 넘겨받음
	}

	@Override
	public ManagerVO selectManager(String id)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateManager(ManagerVO manager) {
		// TODO Auto-generated method stub
		return managerDAO.updateManager(manager); //DAO에 updateManager객체를 넘겨받음
	}
	
	
	
}
