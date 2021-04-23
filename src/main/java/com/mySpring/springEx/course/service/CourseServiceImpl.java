package com.mySpring.springEx.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.course.dao.CourseDAO;
import com.mySpring.springEx.course.vo.CourseVO;


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

}
