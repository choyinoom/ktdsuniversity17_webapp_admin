package com.ktdsuniversity.admin.course.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.common.paging.Criteria;
import com.ktdsuniversity.admin.course.vo.CourseVO;

@Repository("CourseDAO")
public class CourseDAOImpl implements CourseDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllCourseList() throws DataAccessException {
		List<CourseVO> courseList = null;
		courseList = sqlSession.selectList("mapper.course.selectAllCoursesList");
		return courseList;
	}

	@Override
	public int insertCourse(CourseVO courseVO)  throws DataAccessException{
		int result = sqlSession.insert("mapper.course.insertCourse", courseVO);
		return result;
		
	}

	@Override
	public int deleteCoures(int id) throws DataAccessException {
		int result = sqlSession.delete("mapper.course.deleteCourse", id);
		return result;
	}

	@Override
	public CourseVO selectCourse(int id) throws DataAccessException {
		CourseVO vo = sqlSession.selectOne("mapper.course.selectCourse", id);
		return vo;
	}

	@Override
	public int updateCourse(CourseVO courseVO) throws DataAccessException {
		int result = sqlSession.update("mapper.course.updateCourse", courseVO);
		return result;
	}

	@Override
	public int selectNumberOfCourses() throws DataAccessException {
		int count = sqlSession.selectOne("mapper.course.selectNumberOfCourses");
		return count;
	}

	@Override
	public List listCriteria(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.course.listCriteria", criteria);
	}

	@Override
	public List selectCriteriaBySearch(Criteria criteria) throws DataAccessException {
		return sqlSession.selectList("mapper.course.selectCriteriaBySearchCoursesList", criteria);
	}

	@Override
	public List selectBySearchCoursesList(String searchType, String searchText) throws DataAccessException {
		List<CourseVO> coursesBySearchList = null;
		Map<String, String> mapSearch = new HashMap<String, String>();
		mapSearch.put("searchType", searchType);
		mapSearch.put("searchText", searchText);
		coursesBySearchList = sqlSession.selectList("mapper.course.selectBySearchCoursesList", mapSearch);
		return coursesBySearchList;
	}

	@Override
	public int updateByButtonCourse(Map<String, Object> map) throws DataAccessException {
		return sqlSession.update("mapper.course.updateByButtonCourse", map);
	}

	@Override
	public int checkClassRoomOfCourses(HashMap<String, String> param) throws DataAccessException {
		return sqlSession.selectOne("mapper.course.checkClassRoomOfCourses", param);
	}

	@Override
	public int checkClassRoomOfCoursesById(HashMap<String, String> param) throws DataAccessException {
		String id = param.get("id");
		return sqlSession.selectOne("mapper.course.checkClassRoomOfCoursesById", param);
	}

	@Override
	public int updateCourseStatusByDate() throws DataAccessException {
		return sqlSession.update("mapper.course.updateCourseStatus");
	}

	@Override
	public int updateCourseStatusByNumOfApplicants() throws DataAccessException {
		return sqlSession.update("mapper.course.updateCourseStatusByNumOfApplicants");
	}

}
