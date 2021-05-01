package com.ktdsuniversity.admin.manager.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.manager.vo.ManagerVO;
import com.ktdsuniversity.admin.member.vo.MemberVO;

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
		int result = sqlSession.update("mapper.manager.updateManager", managerVO);  //VO�� ����� ������ ��� �ҷ��
		return result;
	}
	
	@Override
	public int updateById(String id) throws DataAccessException {
		int result = sqlSession.update("mapper.manager.updateManger", id);
		return result;
	}

	@Override
	public int logLoginInfo(ManagerVO managerVO) throws DataAccessException {
		int logLoginInfo = 0;
		logLoginInfo = sqlSession.insert("mapper.manager.logLoginInfo", managerVO);
		return logLoginInfo;
	}

	@Override
	public int logLoginFailInfo(ManagerVO managerVO) throws DataAccessException {
		int logLoginFailInfo = 0;
		logLoginFailInfo = sqlSession.insert("mapper.manager.logLoginFailInfo", managerVO);
		return logLoginFailInfo;
	}

	@Override
	public int logLogoutInfo(ManagerVO managerVO) throws DataAccessException {
		int logLogoutInfo = 0;
		logLogoutInfo = sqlSession.insert("mapper.manager.logLogoutInfo", managerVO);
		return logLogoutInfo;
	}

}
