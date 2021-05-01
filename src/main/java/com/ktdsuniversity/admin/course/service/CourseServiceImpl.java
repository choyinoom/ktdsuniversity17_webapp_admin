package com.ktdsuniversity.admin.course.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.dao.CourseDAO;
import com.ktdsuniversity.admin.course.vo.CourseVO;


@Service("courseService")
@Transactional(propagation = Propagation.REQUIRED)
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDAO courseDAO;

	@Override
	public List listCourses() throws DataAccessException {
		List coursesList = null;
		coursesList = courseDAO.selectAllCourseList();
		return coursesList;
	}

	@Override
	public int addCourse(CourseVO course) throws DataAccessException {
		return courseDAO.insertCourse(course);
	}

	@Override
	public int removeCourse(int id) throws DataAccessException {
		return courseDAO.deleteCoures(id);
	}

	@Override
	public CourseVO selectCourse(int id) throws DataAccessException {
		return courseDAO.selectCourse(id);
	}

	@Override
	public int updateCourse(CourseVO courseVO) throws DataAccessException {
		return courseDAO.updateCourse(courseVO);
	}

	@Override
	public int numberOfCourses() throws DataAccessException {
		return courseDAO.selectNumberOfCourses();
	}

	@Override
	public List listBySearchCourses(String searchType, String searchText) throws DataAccessException {
		List cousesBySearchList = null;
		cousesBySearchList = courseDAO.selectBySearchCoursesList(searchType, searchText);
		return cousesBySearchList;
	}

	@Override
	public List listCriteriaBySearch(Criteria criteria) throws DataAccessException {
		List coursesCriteriaBySearch = null;
		coursesCriteriaBySearch = courseDAO.selectCriteriaBySearch(criteria);
		return coursesCriteriaBySearch;
	}

	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return courseDAO.listCriteria(criteria);
	}

	@Override
	public int updateByButtonCourse(Map<String, Object> map) throws DataAccessException {
		return courseDAO.updateByButtonCourse(map);
	}

	@Override
	public int checkClassRoomOfCourses(HashMap<String, String> param) throws DataAccessException {
		return courseDAO.checkClassRoomOfCourses(param);
	}

	@Override
	public int checkClassRoomOfCoursesById(HashMap<String, String> param) throws DataAccessException {
		return courseDAO.checkClassRoomOfCoursesById(param);
	}
}
