package com.mySpring.springEx.courseCategory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.courseCategory.dao.CourseCategoryDAO;

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
}
