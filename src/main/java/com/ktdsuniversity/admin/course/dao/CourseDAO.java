package com.ktdsuniversity.admin.course.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;



public interface CourseDAO {
	
	public List selectAllCourseList() throws DataAccessException;
	public int insertCourse(CourseVO course) throws DataAccessException;
	public int deleteCoures(int id) throws DataAccessException;
	public CourseVO selectCourse(int id) throws DataAccessException;
	public int updateCourse(CourseVO courseVO) throws DataAccessException;
	public int selectNumberOfCourses() throws DataAccessException;
	public List listCriteria(Criteria criteria) throws DataAccessException;
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException;
	public List selectBySearchCoursesList(String searchType, String searchText) throws DataAccessException;
	public int updateByButtonCourse(Map<String, Object> map) throws DataAccessException;
	public int checkClassRoomOfCourses(HashMap<String, String> param) throws DataAccessException;
	public int checkClassRoomOfCoursesById(HashMap<String, String> param) throws DataAccessException;
	public int updateCourseStatusByDate() throws DataAccessException;
	public int updateCourseStatusByNumOfApplicants() throws DataAccessException;
	

}
