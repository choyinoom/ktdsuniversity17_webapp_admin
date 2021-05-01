package com.ktdsuniversity.admin.manager.service;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.manager.vo.ManagerVO;

public interface ManagerService {

	public ManagerVO login(ManagerVO manager) throws DataAccessException;
	public int logLoginInfo(ManagerVO managerVO) throws DataAccessException;
	public int logLoginFailInfo(ManagerVO manager) throws DataAccessException;
	public int logLogoutInfo(ManagerVO managerVO) throws DataAccessException;

	public ManagerVO selectManager(String id);

	public int updateManager(ManagerVO manager);

	

}
