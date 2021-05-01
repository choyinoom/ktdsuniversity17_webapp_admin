package com.ktdsuniversity.admin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.admin.manager.dao.ManagerDAO;
import com.ktdsuniversity.admin.manager.vo.ManagerVO;

@Service("managerService")
@Transactional(propagation = Propagation.REQUIRED)
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private ManagerDAO managerDAO;
	
	@Override
	public ManagerVO login(ManagerVO managerVO) throws DataAccessException {
		return managerDAO.loginById(managerVO); //DAO�� loginById��ü�� �Ѱܹ���
	}

	@Override
	public ManagerVO selectManager(String id)  {
		return null;
	}

	@Override
	public int updateManager(ManagerVO manager) {
		return managerDAO.updateManager(manager); //DAO�� updateManager��ü�� �Ѱܹ���
	}
	
	public int logLoginInfo(ManagerVO managerVO) throws DataAccessException {
		return managerDAO.logLoginInfo(managerVO);
	}

	@Override
	public int logLoginFailInfo(ManagerVO managerVO) throws DataAccessException {
		return managerDAO.logLoginFailInfo(managerVO);
	}

	@Override
	public int logLogoutInfo(ManagerVO managerVO) throws DataAccessException {
		return managerDAO.logLogoutInfo(managerVO);
	}

}
