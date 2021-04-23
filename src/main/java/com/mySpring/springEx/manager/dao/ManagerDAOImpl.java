package com.mySpring.springEx.manager.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mySpring.springEx.manager.vo.ManagerVO;
import com.mySpring.springEx.member.vo.MemberVO;

@Repository("managerDAO")
public class ManagerDAOImpl implements ManagerDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ManagerVO loginById(ManagerVO managerVO) throws DataAccessException {
		ManagerVO vo = sqlSession.selectOne("mapper.manager.loginById", managerVO);
		return vo;
	}
	
	@Override
	public int updateManager(ManagerVO managerVO) throws DataAccessException {
		System.out.println("In DAO *********************" + managerVO.getId());
		int result = sqlSession.update("mapper.manager.updateManager", managerVO);  //VO�� ����� ������ ��� �ҷ��
		return result;
	}
	
	@Override
	public int updateById(String id) throws DataAccessException {
		int result = sqlSession.update("mapper.manager.updateManger", id);
		return result;
	}
}
