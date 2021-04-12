package com.mySpring.springEx.courseCategory.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface CourseCategoryDAO {

	List selectAllCourseCategory() throws DataAccessException;

}
