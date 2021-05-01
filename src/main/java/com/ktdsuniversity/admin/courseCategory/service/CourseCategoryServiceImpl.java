package com.ktdsuniversity.admin.courseCategory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.admin.courseCategory.dao.CourseCategoryDAO;
import com.ktdsuniversity.admin.courseCategory.vo.CourseCategoryVO;

@Service("courseCategoryService")
@Transactional(propagation = Propagation.REQUIRED)
public class CourseCategoryServiceImpl implements CourseCategoryService{
	

	@Autowired
	CourseCategoryDAO courseCategoryDAO;

	@Override
	public List listAllCourseCategories() throws DataAccessException {
		List courseCategoryList = courseCategoryDAO.selectAllCourseCategory();
		return courseCategoryList;
	}

	@Override
	public int addCourseCategory(CourseCategoryVO courseCategoryVO) {
		return courseCategoryDAO.addCourseCategoryVO(courseCategoryVO);
	}

	@Override
	public CourseCategoryVO selectCourseCategory(int id) {
		return courseCategoryDAO.selectCourseCategory(id);
	}

	@Override
	public int updateCourseCategory(CourseCategoryVO courseCategoryVO) {
		return courseCategoryDAO.updateCourseCategory(courseCategoryVO);
	}

	@Override
	public int removeCourseCategory(int id) {
		return courseCategoryDAO.deleteCourseCategory(id);
	}
}
