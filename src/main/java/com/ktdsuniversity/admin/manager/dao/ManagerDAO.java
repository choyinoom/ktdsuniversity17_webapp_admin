package com.ktdsuniversity.admin.manager.dao;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.manager.vo.ManagerVO;
import com.ktdsuniversity.admin.member.vo.MemberVO;

public interface ManagerDAO {

	public ManagerVO loginById(ManagerVO managerVO) throws DataAccessException;
	public int logLoginInfo(ManagerVO managerVO) throws DataAccessException;
	public int logLoginFailInfo(ManagerVO managerVO) throws DataAccessException;
	public int logLogoutInfo(ManagerVO managerVO) throws DataAccessException;

	public int updateManager(ManagerVO managerVO) throws DataAccessException;

	public int updateById(String id) throws DataAccessException;

}
