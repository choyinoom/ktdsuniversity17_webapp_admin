package com.mySpring.springEx.courseCategory.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mySpring.springEx.courseCategory.vo.CourseCategoryVO;

@Repository("courseCatagoryDAO")
public class CourseCategoryDAOImpl implements CourseCategoryDAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List selectAllCourseCategory() throws DataAccessException{
		
		List<CourseCategoryVO> courseCategoryList = null;
		courseCategoryList = sqlSession.selectList("mapper.courseCategory.selectAllCourseCategory");
		return courseCategoryList;
	}
}
