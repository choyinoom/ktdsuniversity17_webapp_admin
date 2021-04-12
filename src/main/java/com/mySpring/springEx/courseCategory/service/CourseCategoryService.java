package com.mySpring.springEx.courseCategory.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface CourseCategoryService {

	List listAllCourseCategories() throws DataAccessException;

}
