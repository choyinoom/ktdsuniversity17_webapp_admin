package com.mySpring.springEx.manager.dao;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.manager.vo.ManagerVO;
import com.mySpring.springEx.member.vo.MemberVO;

public interface ManagerDAO {

	public ManagerVO loginById(ManagerVO managerVO) throws DataAccessException;

	public int updateManager(ManagerVO managerVO) throws DataAccessException;

	public int updateById(String id) throws DataAccessException;

}
