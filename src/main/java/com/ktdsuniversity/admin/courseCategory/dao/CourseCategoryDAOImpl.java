package com.ktdsuniversity.admin.courseCategory.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.courseCategory.vo.CourseCategoryVO;

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

	@Override
	public int addCourseCategoryVO(CourseCategoryVO courseCategoryVO) {
		int result = 0;
		result = sqlSession.insert("mapper.courseCategory.insertCourseCategory", courseCategoryVO);
		return result;
	}

	@Override
	public CourseCategoryVO selectCourseCategory(int id) {
		CourseCategoryVO courseCategoryVO = sqlSession.selectOne("mapper.courseCategory.selectCourseCategory", id);
		return courseCategoryVO;
	}

	@Override
	public int updateCourseCategory(CourseCategoryVO courseCategoryVO) {
		int result = sqlSession.update("mapper.courseCategory.updateCourseCategory", courseCategoryVO);
		return result;
	}

	@Override
	public int deleteCourseCategory(int id) throws DataAccessException {
		int result = sqlSession.delete("mapper.courseCategory.deleteCourseCategory", id);
		return result;
	}
}
