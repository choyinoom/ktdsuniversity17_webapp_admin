package com.ktdsuniversity.admin.course.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;



public interface CourseService {

	public List listCourses() throws DataAccessException;
	public int addCourse(CourseVO course) throws DataAccessException;
	public int removeCourse(int id) throws DataAccessException;
	public CourseVO selectCourse(int id) throws DataAccessException;
	public int updateCourse(CourseVO courseVO) throws DataAccessException;
	public int numberOfCourses() throws DataAccessException;
	public List listBySearchCourses(String searchType, String searchText) throws DataAccessException;
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException;
	public List listCriteria(Criteria criteria) throws DataAccessException;
	public int updateByButtonCourse(Map<String, Object> map) throws DataAccessException;
	public int checkClassRoomOfCourses(HashMap<String, String> param) throws DataAccessException;
	public int checkClassRoomOfCoursesById(HashMap<String, String> param) throws DataAccessException;
}
